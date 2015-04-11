# -*- coding:utf-8 -*-
import re

class Phone(object):
    INVALID = "0" * 10

    def __init__(self, number):
        self.number = number

    @property
    def number(self):
        return self._number
    
    @number.setter
    def number(self, value):
        self._number = self._sanitize(value)

    @classmethod
    def _sanitize(cls, value):
        num = re.sub(r"\D", "", value) 
        length = len(num)

        if length == 11 and num.startswith("1"):
            return num[1:]

        if length != 10:
            return cls.INVALID

        return num

    def area_code(self):
        return self._split()[0]

    def pretty(self):
        return "(%s) %s-%s" % self._split()

    def _split(self):
        return self.number[:3], self.number[3:6], self.number[6:]
    



    
