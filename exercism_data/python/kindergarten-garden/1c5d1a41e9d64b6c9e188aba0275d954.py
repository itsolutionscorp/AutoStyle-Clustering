#!/usr/bin/env python2
# -*- coding: utf-8 -*-

class Garden(object):

    __windowsill = []
    __students = []
    __plant_map = {
            'R' : 'Radishes',
            'G' : 'Grass',
            'C' : 'Clover',
            'V' : 'Violets',
            '.' : 'Empty'
            }

    def __init__(self, windowsill, students=None):
        self.__windowsill = windowsill.split('\n')
        if not students is None:
            self.__students = sorted(students)
        else:
            self.__students = sorted(['Alice', 'Bob', 'Charlie', 'David',
                               'Eve', 'Fred', 'Ginny', 'Harriet',
                               'Ileana', 'Joseph', 'Kincaid', 'Larry'])

    def plants(self, nom):
        sid = self.__students.index(nom)*2
        plt = self.__windowsill[0][sid:sid+2] + self.__windowsill[1][sid:sid+2]
        return [self.__plant_map[x] for x in plt]

if __name__ == '__main__':
    print '\n\tThis script is not meant to be used this way.\n'
