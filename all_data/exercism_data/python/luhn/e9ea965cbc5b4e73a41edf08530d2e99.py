#!/usr/bin/env python3
class Luhn:
    def __init__(self, num=None):
        if num == None:
            self.status = False
        else:
            self.status = True
            self.number = list(map(int, list(str(num))))[::-1]
            self.reverse_number = []
            for no, i in enumerate(self.number):
                if no % 2 == 1 and i * 2 < 10:
                    self.reverse_number.append(i * 2)
                elif no % 2 == 1 and i * 2 > 9:
                    self.reverse_number.append(i * 2 - 9)
                else:
                    self.reverse_number.append(i)

    def addends(self):
        if self.status:
            return self.reverse_number[::-1]

    def checksum(self):
        if self.status:
            return sum(self.reverse_number) % 10

    def is_valid(self):
        if self.status:
            return self.checksum() == 0

    def create(self, num):
        if Luhn(num).is_valid():
            return num
        for i in range(10):
            num = num * 10 + i
            if Luhn(num).is_valid():
                return num
            else:
                num = num // 10
