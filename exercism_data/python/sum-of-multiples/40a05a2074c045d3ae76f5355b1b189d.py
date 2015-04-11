#!/usr/bin/env python2
# -*- coding: utf-8 -*-

class SumOfMultiples(object):

    def __init__(self, *args, **kwargs):
        self.__mof = []
        if len(args) == 0:
            self.__mof.append(3)
            self.__mof.append(5)
        else:
            for x in args:
                self.__mof.append(x)

    def to(self, num):
        lst = []
        for x in self.__mof:
            lst += [x for x in range(x, num, x)]
        return sum(set(lst))

if __name__ == '__main__':
    print '\n\tThis script is not meant to be used this way.\n'
