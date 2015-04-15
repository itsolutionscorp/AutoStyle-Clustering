from __future__ import unicode_literals

import re

class Phone(object):
    """ Validates and presents 10-digit phone numbers """
    def __init__(self, number):
        self.number = ''.join(n for n in number if n.isdigit())
        if len(self.number) == 11 and self.number[0] == '1':
            self.number = self.number[1:]
        if len(self.number) != 10:
            self.number = '0' * 10

    def area_code(self):
        return self.number[:3]

    def pretty(self):
        return "({}) {}-{}".format(self.number[:3], self.number[3:6],
                                   self.number[6:])
