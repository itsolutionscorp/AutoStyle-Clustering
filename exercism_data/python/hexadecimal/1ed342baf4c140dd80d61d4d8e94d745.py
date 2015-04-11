def val(char):
    hex_map = {
        'a': 10,
        'b': 11,
        'c': 12,
        'd': 13,
        'e': 14,
        'f': 15
    }
    char = char.lower()
    if char in hex_map:
        return hex_map[char]
    else:
        return int(char)
    

def hexa(hex):
    return sum(val(char) * 16 ** index for index, char in enumerate(reversed(hex)))
    

print hexa('carrot')

"""

    if re.compile('[^01]').search(num):
        raise ValueError('non binary input')

    return sum(int(char) * 2 ** index for index, char in enumerate(reversed(num)))


# To avoid trivial solutions, try to solve this problem without the
# function int(s, base=16)

import unittest

from hexadecimal import hexa


class HexadecimalTest(unittest.TestCase):

    def test_valid_hexa1(self):
        self.assertEqual(1, hexa('1'))

    def test_valid_hexa2(self):
        self.assertEqual(12, hexa('c'))

    def test_valid_hexa3(self):
        self.assertEqual(16, hexa('10'))

    def test_valid_hexa4(self):
        self.assertEqual(175, hexa('af'))

    def test_valid_hexa5(self):
        self.assertEqual(256, hexa('100'))

    def test_valid_hexa6(self):
        self.assertEqual(105166, hexa('19ACE'))

    def test_valid_hexa7(self):
        self.assertEqual(0, hexa('000000'))

    def test_valid_hexa8(self):
        self.assertEqual(16776960, hexa('ffff00'))

    def test_valid_hexa9(self):
        self.assertEqual(65520, hexa('00fff0'))

    def test_invalid_hexa(self):
        with self.assertRaises(ValueError):
            hexa('carrot')


if __name__ == '__main__':
    unittest.main()

"""
