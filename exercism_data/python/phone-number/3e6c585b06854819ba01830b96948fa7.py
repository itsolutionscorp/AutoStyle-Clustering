#!/usr/bin/env python3

from string import digits


class Phone(str):
    def __new__(klass, number):
        number = klass._clean(number)
        if len(number) == 11 and number.startswith('1'):
            number = number[1:]
        if len(number) != 10:
            number = '0000000000'
        return super().__new__(klass, number)

    @staticmethod
    def _clean(number):
        return ''.join(d for d in number if d in digits)

    def area_code(self):
        return self[:3]

    @property
    def number(self):
        return self

    def pretty(self):
        return '({}) {}-{}'.format(self[:3], self[3:6], self[6:])
