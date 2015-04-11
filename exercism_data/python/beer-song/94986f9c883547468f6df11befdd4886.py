#!/usr/bin/env python
# encoding=utf-8


class Beer(object):
    """Beer class"""

    def __init__(self):
        pass


    def verse(self, verse_number):
        """Return a verse of the song."""
        if verse_number == 0:
            line1 = "No more bottles of beer on the wall, no more bottles of beer.\n"
            line2 = "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
        elif verse_number == 1:
            line1 = "1 bottle of beer on the wall, 1 bottle of beer.\n"
            line2 = "Take it down and pass it around, no more bottles of beer on the wall.\n"
        elif verse_number == 2:
            line1 = "%d bottles of beer on the wall, %d bottles of beer.\n" % (verse_number, verse_number)
            line2 = "Take one down and pass it around, 1 bottle of beer on the wall.\n"
        else:
            line1 = "%d bottles of beer on the wall, %d bottles of beer.\n" % (verse_number, verse_number)
            line2 = "Take one down and pass it around, %d bottles of beer on the wall.\n" % (verse_number - 1)
        return line1 + line2


    def sing(self, verse_start, verse_end = 0):
        """Return a part of the song"""
        beer = Beer()
        song = ''
        for counter in range(verse_start, verse_end - 1, -1):
            song += beer.verse(counter) + "\n"
        return song
