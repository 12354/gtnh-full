#!/usr/bin/env python3
"""
Extract humidity and temperature needs for each bee species from GTBeeDefinition.java
"""

import re
from pathlib import Path


def extract_bee_species_needs(java_file_path: str) -> list[dict]:
    """
    Parse the GTBeeDefinition.java file and extract humidity/temperature for each species.

    Returns a list of dictionaries with species name, temperature, and humidity.
    """
    with open(java_file_path, 'r') as f:
        content = f.read()

    results = []

    # Pattern to match each enum constant (bee species definition)
    # Format: NAME(GTBranchDefinition.BRANCH, "DisplayName", ...
    enum_section_match = re.search(r'public enum GTBeeDefinition.*?\{(.*?);\s*private',
                                    content, re.DOTALL)
    if not enum_section_match:
        print("Could not find enum section")
        return results

    enum_section = enum_section_match.group(1)

    # Pattern to match individual bee definitions
    # NAME(GTBranchDefinition.BRANCH, "DisplayName", ...
    bee_pattern = re.compile(
        r'^\s*([A-Z][A-Za-z0-9_]*)\s*\(\s*GTBranchDefinition\.(\w+)',
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

        # Extract temperature setting (handles both short and long form)
        # Short: setTemperature(HOT) or setTemperature(NORMAL)
        # Long: setTemperature(EnumTemperature.NORMAL)
        temp_match = re.search(r'\.setTemperature\s*\(\s*(?:EnumTemperature\.)?(\w+)\s*\)', bee_section)
        temperature = temp_match.group(1).lower() if temp_match else 'normal'

        # Extract humidity setting (handles both short and long form)
        # Short: setHumidity(DAMP) or setHumidity(ARID)
        # Long: setHumidity(EnumHumidity.NORMAL)
        humidity_match = re.search(r'\.setHumidity\s*\(\s*(?:EnumHumidity\.)?(\w+)\s*\)', bee_section)
        humidity = humidity_match.group(1).lower() if humidity_match else 'normal'

        results.append({
            'species': bee_name,
            'temperature': temperature,
            'humidity': humidity
        })

    return results


def main():
    java_file = Path(__file__).parent / "GT5-Unofficial/src/main/java/gregtech/loaders/misc/GTBeeDefinition.java"

    if not java_file.exists():
        print(f"Error: Could not find {java_file}")
        return

    bee_data = extract_bee_species_needs(str(java_file))

    # Output as txt file in format: species;temperature;humidity
    output_txt = Path(__file__).parent / "gt_bee_species_needs.txt"
    with open(output_txt, 'w') as f:
        for bee in bee_data:
            f.write(f"{bee['species']};{bee['temperature']};{bee['humidity']}\n")

    print(f"Found {len(bee_data)} bee species")
    print(f"Output written to: {output_txt}")


if __name__ == "__main__":
    main()
