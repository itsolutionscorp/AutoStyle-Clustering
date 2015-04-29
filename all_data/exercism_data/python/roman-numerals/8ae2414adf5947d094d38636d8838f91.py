__author__ = 'angelo'


arabic_to_roman = [(1000, "M"),(900, "CM"),(500, "D"),(400, "CD"),(100, "C"),(90, "XC"),(50, "L"),(40, "XL"),(10, "X"),(9, "IX"),(5, "V"),(4, "IV"),(1, "I")]


class RomanNumeral:

    def __init__(self, num):
        arabic = num
        self.roman = ''
        for n in arabic_to_roman:
            while arabic >= n[0]:
                    arabic -= n[0]
                    self.roman += n[1]

    def __str__(self):
        return self.roman
