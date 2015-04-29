#!/usr/bin/python

class Phone(object):
    def __init__(self, num_in):
        self.number = "0000000000"
        self._cleanup_(num_in)
    
    def _cleanup_(self, num_in):
        tmp = ''.join([x for x in num_in if x in "0123456789"])
        if len(tmp) == 10:
            self.number = tmp
        elif len(tmp) == 11 and tmp[0] == "1":
            self.number = tmp[1:]

    def area_code(self):
        return self.number[0:3]

    def pretty(self):
        return "({0:}) {1:}-{2:}".format(self.number[0:3], self.number[3:6],
                                            self.number[6:])
