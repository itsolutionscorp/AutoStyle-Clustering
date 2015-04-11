# -*- coding:utf-8 -*-
import re

class Phone(object):
    INVALID = "0" * 10

    def __init__(self, input):
        self.number = self._sanitize(input)

    def _sanitize(self, input):
        num = re.sub(r"\D", "", input) 
        length = len(num)

        if length == 11 and num.startswith("1"):
            return num[1:]

        if length != 10:
            return self.INVALID

        return num

    def _split(self):
        return self.number[:3], self.number[3:6], self.number[6:]
    
    def area_code(self):
        return self._split()[0]

    def pretty(self):
        return "(%s) %s-%s" % self._split()



    
