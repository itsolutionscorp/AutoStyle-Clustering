#! /usr/bin/env python

#0 'No more         s                   no more         s
#1        1        ''                         1         ''
#>1       2         s                         2         s
WALL = "{b} bottle{s} of beer on the wall, {b} bottle{s} of beer.\n"

#1            it                       no more         s
#1            one                            1         ''
#2>           one                            3         s
TAKE = "Take {b1} down and pass it around, {b2} bottle{s} of beer on the wall.\n"

#
STORE = "Go to the store and buy some more, 99 bottles of beer on the wall.\n"


class Couplet(object):

    def __init__(self, n):
        self.lines = ""
        if n == 0:
            self.lines += WALL.format(b='no more', s='s'*(n != 1)).capitalize()
            self.lines += STORE
        elif n == 1:
            self.lines += WALL.format(b=n, s='s'*(n != 1))
            self.lines += TAKE.format(b1='it', b2='no more', s='s'*(n-1 != 1))
        else:
            self.lines += WALL.format(b=n, s='s'*(n != 1))
            self.lines += TAKE.format(b1='one', b2=n-1, s='s'*(n-1 != 1))


class Beer(object):
    def sing(self, start, stop=0):
        return '\n'.join([self.verse(i)
                          for i in range(start, stop - 1, -1)]) + '\n'

    def verse(self, num):
        return Couplet(num).lines
