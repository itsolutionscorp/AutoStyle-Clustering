#!/usr/bin/env python

class Phone:
    def __init__(self, number):
        clean_number = "".join([digit for digit in number if digit.isdigit()])
        if len(clean_number) == 10:
            self.number = clean_number
        elif len(clean_number) == 11 and clean_number.startswith('1'):
            self.number = clean_number[1:]
        else:
            self.number = 10 * "0"

    def pretty(self):
        return "({0}) {1}-{2}".format(self.number[:3],
                                      self.number[3:6],
                                      self.number[6:])

    def area_code(self):
        return "{0}".format(self.number[:3])
