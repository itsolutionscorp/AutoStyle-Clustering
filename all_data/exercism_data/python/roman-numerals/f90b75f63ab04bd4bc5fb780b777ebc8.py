class RomanNumeral:
    """ RomanNumeral converts an arabic number into a Roman numeral """

    NUMERALS = {1000: 'M', 900: 'CM', 500:'D', 400:'CD', 100:'C', 90:'XC', 50:'L', 40:'XL', 10:'X', 9:'IX',  5:'V', 4:'IV', 1:'I'}

    def __init__(self, number):
        self.number = number

    def convert(self):
        result = ""
        temp = self.number
        for arabic, roman in reversed(sorted(self.NUMERALS.items())):
            x = temp / arabic
            temp = temp % arabic
            result = result + (roman * x)
        return result

    def __str__(self):
        return self.convert()
