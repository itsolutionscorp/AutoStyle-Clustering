#!/usr/bin/env python3
class Luhn(object):
    def __init__(self, num):
        self.num = num

    def addends(self):
        digits = [int(c) for c in str(self.num)]
        for i in range(-1, -len(digits) - 1, -1):
            if i % 2 == 0:
                digits[i] *= 2
            if digits[i] >= 10:
                digits[i] -= 9
        return digits

    def is_valid(self):
        return sum(self.addends()) % 10 == 0

    def checksum(self):
        return sum(self.addends()) % 10

    @staticmethod
    def create(part):
        for full in range(10 * part, 10 * part + 10):
            if Luhn(full).is_valid():
                return full
