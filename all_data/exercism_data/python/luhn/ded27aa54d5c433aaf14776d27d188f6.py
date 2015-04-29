#!/usr/bin/python
class Luhn(object):
    def __init__(self, number = None):
        self.number = number

    def addends(self, flag = 1):
        if flag not in [0, 1]:
            raise(ValueError)
        split_string_number = list(str(self.number))[::-1]
        split_number = [(index, int(digit)) for index, digit in enumerate(split_string_number)]
        for index, digit in split_number:
            if index % 2 == flag:
                if digit * 2 < 10:
                    split_number[index] = (index, digit * 2)
                else:
                    split_number[index] = (index, (digit * 2) - 9)
        return [digit for index, digit in split_number[::-1]]

    def checksum(self):
        return sum(self.addends())%10

    def is_valid(self):
        return self.checksum() == 0
    
    @staticmethod
    def create(number):
        last_digit = (10 - sum(Luhn(number).addends(flag = 0))%10)%10
        return number*10 + last_digit
