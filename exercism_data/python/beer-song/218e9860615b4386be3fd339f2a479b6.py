#! /usr/bin/env python

#0  'No more         s                   no more         s
#1         1        ''                         1         ''
#>1        2         s                         2         s
WALL = "{b1} bottle{s} of beer on the wall, {b2} bottle{s} of beer.\n"

#1             it                       no more         s
#1             one                            1         ''
#2>            one                            3         s
TAKE = "Take {b1} down and pass it around, {b2} bottle{s} of beer on the wall.\n"

#
STORE = "Go to the store and buy some more, 99 bottles of beer on the wall.\n"


class Couplet(object):

    def __init__(self, n):
        self.lines = ""
        if n == 0:
            self.lines += WALL.format(b1='No more', b2='no more', s='s')
            self.lines += STORE
        elif n == 1:
            self.lines += WALL.format(b1=1, b2=1, s='')
            self.lines += TAKE.format(b1='it', b2='no more', s='s')
        elif n == 2:
            self.lines += WALL.format(b1=n, b2=n, s='s')
            self.lines += TAKE.format(b1='one', b2=1, s='')
        else:
            self.lines += WALL.format(b1=n, b2=n, s='s')
            self.lines += TAKE.format(b1='one', b2=n-1, s='s')


class Beer(object):
    def sing(self, start, stop=0):
        #stanza = ""
        #for i in range(start, stop - 1, -1):
        #    stanza += self.verse(i) + "\n"
        #return stanza
        return '\n'.join([self.verse(i) for i in range(start, stop - 1, -1)]) + '\n'

    def verse(self, num):
        return Couplet(num).lines
