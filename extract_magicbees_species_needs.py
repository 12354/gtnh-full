#!/usr/bin/env python3
"""
Extract humidity and temperature needs for each bee species from MagicBees BeeSpecies.java
"""

import re
from pathlib import Path


def extract_bee_species_needs(java_file_path: str) -> list[dict]:
    """
    Parse the BeeSpecies.java file and extract humidity/temperature for each species.

    Returns a list of dictionaries with species name, temperature, and humidity.
    """
    with open(java_file_path, 'r') as f:
        content = f.read()

    results = []

    # Pattern to match each enum constant with temperature and humidity
    # Format: NAME("DisplayName", "binomial", Classification, ..., EnumTemperature.TEMP, EnumHumidity.HUM, ...)
    bee_pattern = re.compile(
        r'^\s*([A-Z][A-Z0-9_]*)\s*\([^)]*EnumTemperature\.(\w+)\s*,\s*EnumHumidity\.(\w+)',
        re.MULTILINE
    )

    for match in bee_pattern.finditer(content):
        bee_name = match.group(1)
        temperature = match.group(2).lower()
        humidity = match.group(3).lower()

        results.append({
            'species': bee_name,
            'temperature': temperature,
            'humidity': humidity
        })

    return results


def main():
    java_file = Path(__file__).parent / "MagicBees/src/main/java/magicbees/bees/BeeSpecies.java"

    if not java_file.exists():
        print(f"Error: Could not find {java_file}")
        return

    bee_data = extract_bee_species_needs(str(java_file))

    # Output as txt file in format: species;temperature;humidity
    output_txt = Path(__file__).parent / "magicbees_species_needs.txt"
    with open(output_txt, 'w') as f:
        for bee in bee_data:
            f.write(f"{bee['species']};{bee['temperature']};{bee['humidity']}\n")

    print(f"Found {len(bee_data)} bee species")
    print(f"Output written to: {output_txt}")


if __name__ == "__main__":
    main()
