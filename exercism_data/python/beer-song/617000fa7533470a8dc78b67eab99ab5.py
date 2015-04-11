#!/usr/bin/env python3

VERSE = """\
{} on the wall, {}.
{}, {} on the wall.
"""

USUAL = "Take one down and pass it around"
ASOCIAL = "Take it down and pass it around"
EMERGENCY = "Go to the store and buy some more"


def nbottles(n):
    if n>1:
        return "{} bottles of beer".format(n)
    if n==1:
        return "1 bottle of beer"
    return "No more bottles of beer"


class Beer:
    def verse(self, n):
        before = nbottles(n)
        if n>1:
            task = USUAL
            after = nbottles(n-1)
        elif n==1:
            task = ASOCIAL
            after = nbottles(n-1)
        else:
            task = EMERGENCY
            after = nbottles(99)

        return VERSE.format(before, before.lower(), task, after.lower())

    def sing(self, fro, to=0):
        return '\n'.join(self.verse(i) for i in range(fro, to-1, -1)) + '\n'
