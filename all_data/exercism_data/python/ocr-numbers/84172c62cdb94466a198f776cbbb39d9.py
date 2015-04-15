#!/usr/bin/python3


number2lines = {
    '0': [" _ ", "| |", "|_|", "   "],
    '1': ["   ", "  |", "  |", "   "],
}

lines2number = {tuple(v): k for k, v in number2lines.items()}


def number(lst_of_lines):
    if tuple(lst_of_lines) not in lines2number:
        if len(lst_of_lines) == 4 and all(len(line) == 3 for line in lst_of_lines):
            return '?'
        else:
            raise ValueError('Glyph not recognized. '
                             'It should have size 3x4 chars')
    return lines2number[tuple(lst_of_lines)]


def grid(digit_str):
    if digit_str not in number2lines:
        raise ValueError('Character not implementeted yet')
    if len(digit_str) == 1:
        return number2lines[digit_str]
    else:
        return [number2lines[digit] for digit in digit_str]


if __name__ == "__main__":
    pass
