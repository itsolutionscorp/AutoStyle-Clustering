#!/usr/bin/python3


def parse_octal(digit_str):
    number = 0
    for power, digit in enumerate(digit_str[::-1]):
        try:
            assert int(digit) in range(0, 8)
            number += pow(8, power) * int(digit)
        except AssertionError or TypeError:
            raise ValueError('Not a valid octal number')
    return number


if __name__ == "__main__":
    pass
