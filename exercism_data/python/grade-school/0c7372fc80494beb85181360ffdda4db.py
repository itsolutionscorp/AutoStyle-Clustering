#!/usr/bin/env python2
# -*- coding: utf-8 -*-

import inspect

class School(object):

    __school_name = ""
    __school_db = {}
    __user = None

    def __init__(self, school_name):
        self.__school_name = school_name

    def _caller(self):
        caller = inspect.stack()[2][3]
        if not caller in self.__school_db.keys():
            self.__school_db[caller] = {}
        self.__user = caller

    @property
    def db(self):
        self._caller()
        tmp_db = self.__school_db[self.__user]
        return { k:set(tmp_db[k]) for k in tmp_db.keys() }

    def add(self, name, grade):
        self._caller()
        tmp_db = self.__school_db[self.__user]
        if not grade in tmp_db:
            tmp_db[grade] = []
        tmp_db[grade].append(name)

    def sort(self):
        self._caller()
        tmp_db = self.__school_db[self.__user]
        return { k:tuple(sorted(tmp_db[k])) for k in sorted(tmp_db.keys())}

    def grade(self, number):
        self._caller()
        tmp_db = self.__school_db[self.__user]
        if not number in tmp_db:
            return set()
        else:
            return set(tmp_db[number])

if __name__ == '__main__':
    print '\n\tThis script is not meant to be used this way.\n'
