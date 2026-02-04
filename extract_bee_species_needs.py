#!/usr/bin/env python3
"""
Extract humidity and temperature needs for each bee species from ExtraBeeDefinition.java
"""

import re
import json
from pathlib import Path


def extract_bee_species_needs(java_file_path: str) -> list[dict]:
    """
    Parse the ExtraBeeDefinition.java file and extract humidity/temperature for each species.

    Returns a list of dictionaries with species name, temperature, and humidity.
    """
    with open(java_file_path, 'r') as f:
        content = f.read()

    results = []

    # Pattern to match each enum constant (bee species definition)
    # Matches: NAME(Branch, "binomial", dominant, color1, color2) { ... }
    # We need to capture the name and the setSpeciesProperties block

    # Split by enum constants - each starts with a capitalized name followed by (
    # The enum ends at MYSTICAL which is followed by ;
    enum_section_match = re.search(r'public enum ExtraBeeDefinition.*?\{(.*?)private static final EnumSet',
                                    content, re.DOTALL)
    if not enum_section_match:
        print("Could not find enum section")
        return results

    enum_section = enum_section_match.group(1)

    # Pattern to match individual bee definitions
    # Matches: NAME(ExtraBeeBranchDefinition.BRANCH or BeeBranchDefinition.BRANCH, "binomial", ...
    bee_pattern = re.compile(
        r'^    ([A-Z][A-Z0-9_]*)\s*\(\s*'  # Bee name
        r'(?:ExtraBeeBranchDefinition|BeeBranchDefinition)\.(\w+)',  # Branch
        re.MULTILINE
    )

    # Find all bee names and their positions
    bee_matches = list(bee_pattern.finditer(enum_section))

    for i, match in enumerate(bee_matches):
        bee_name = match.group(1)
        branch = match.group(2)

        # Get the section for this bee (from current match to next match or end)
        start = match.start()
        if i + 1 < len(bee_matches):
            end = bee_matches[i + 1].start()
        else:
            end = len(enum_section)

        bee_section = enum_section[start:end]

        # Extract temperature setting
        temp_match = re.search(r'\.setTemperature\s*\(\s*EnumTemperature\.(\w+)\s*\)', bee_section)
        temperature = temp_match.group(1) if temp_match else None

        # Extract humidity setting
        humidity_match = re.search(r'\.setHumidity\s*\(\s*EnumHumidity\.(\w+)\s*\)', bee_section)
        humidity = humidity_match.group(1) if humidity_match else None

        results.append({
            'species': bee_name,
            'branch': branch,
            'temperature': temperature,
            'humidity': humidity
        })

    return results


def main():
    java_file = Path(__file__).parent / "Binnie/src/main/java/binnie/extrabees/genetics/ExtraBeeDefinition.java"

    if not java_file.exists():
        print(f"Error: Could not find {java_file}")
        return

    bee_data = extract_bee_species_needs(str(java_file))

    # Print summary
    print(f"Found {len(bee_data)} bee species\n")

    # Print as formatted table
    print(f"{'Species':<20} {'Branch':<15} {'Temperature':<15} {'Humidity':<15}")
    print("-" * 65)

    for bee in bee_data:
        temp = bee['temperature'] or '(default)'
        humidity = bee['humidity'] or '(default)'
        print(f"{bee['species']:<20} {bee['branch']:<15} {temp:<15} {humidity:<15}")

    # Also output as JSON
    output_json = Path(__file__).parent / "bee_species_needs.json"
    with open(output_json, 'w') as f:
        json.dump(bee_data, f, indent=2)

    print(f"\nJSON output written to: {output_json}")

    # Print statistics
    print("\n--- Statistics ---")

    temps = [b['temperature'] for b in bee_data if b['temperature']]
    humidities = [b['humidity'] for b in bee_data if b['humidity']]

    print(f"Species with explicit temperature: {len(temps)}")
    print(f"Species with explicit humidity: {len(humidities)}")

    if temps:
        from collections import Counter
        temp_counts = Counter(temps)
        print("\nTemperature distribution:")
        for temp, count in sorted(temp_counts.items()):
            print(f"  {temp}: {count}")

    if humidities:
        from collections import Counter
        humidity_counts = Counter(humidities)
        print("\nHumidity distribution:")
        for hum, count in sorted(humidity_counts.items()):
            print(f"  {hum}: {count}")


if __name__ == "__main__":
    main()
