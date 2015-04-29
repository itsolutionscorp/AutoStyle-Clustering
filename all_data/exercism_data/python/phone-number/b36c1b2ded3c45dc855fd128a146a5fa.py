# -*- coding:utf-8 -*-
import re

class Phone(object):
    def __init__(self, input):
        self.number = self._sanitize(input)

    def _sanitize(self, input):
        invalid = "0" * 10
        n = re.sub(r"\D", "", input) 

        count = len(n)
        if count == 11:
            if n.startswith("1"):
                n = n[1:]
            else:
                n = invalid
        elif count == 9:
            n = invalid

        return n

    def _split(self):
        return self.number[:3], self.number[3:6], self.number[6:]
    def area_code(self):
        return self._split()[0]

    def pretty(self):
        return "(%s) %s-%s" % self._split()



    
