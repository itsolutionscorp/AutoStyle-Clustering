#!/usr/bin/env python2
# -*- coding: utf-8 -*-

class Luhn(object):

    def __init__(self, num=None):
        self.__num = num

    @property
    def chk_ex(self):
        if self.__num == None:
            raise Exception("Number to check not specified.")

    def addends(self):
        self.chk_ex
        tmp = list(str(self.__num))[::-1]
        for x in range(1, len(tmp), 2):
            tmp[x] = int(tmp[x])*2
            if tmp[x] >= 10:
                tmp[x] -= 9
        return [int(x) for x in tmp][::-1]

    @property
    def chk_bit(self):
        self.chk_ex
        return sum(self.addends())

    def checksum(self):
        self.chk_ex
        return self.chk_bit % 10

    @staticmethod
    def create(num):
        cb = Luhn(int(str(num)+'0')).chk_bit
        n = ((((cb/10)+1)*10)-cb) % 10
        n00b = int(str(num)+str(n))
        return n00b

    def is_valid(self):
        self.chk_ex
        if self.checksum() % 10 == 0:
            return True
        else:
            return False

if __name__ == '__main__':
    print '\n\tThis script is not meant to be used this way.\n'
