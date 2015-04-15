class Luhn(object):
    def __init__(self, number):
        self.number = number
        
    def is_valid(self):
        return self.checksum() == 0
    
    def checksum(self):
        return sum(self.addends()) % 10

    def addends(self):
        luhn = []
        for index, character in enumerate(reversed(str(self.number))):
            if index % 2 == 1:
                digit = 2 * int(character)
                if digit >= 10:
                    digit -= 9
                luhn.append(digit)
            else:
                luhn.append(int(character))
        return luhn

    @classmethod
    def create(cls, number):
        shifted_number = number * 10
        remainder = (10 - cls(shifted_number).checksum()) % 10
        return shifted_number + remainder
