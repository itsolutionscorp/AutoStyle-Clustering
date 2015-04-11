#!/usr/bin/env python

class Binary:
    def __init__(self, number):
        self.number = number
    
    def __int__(self):
        value = 0
        for power, digit in enumerate(reversed(self.number)):
            if digit == "1":
                value += 2 ** power
        return value
