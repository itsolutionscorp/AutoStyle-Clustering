# -*- coding: utf-8 -*-

from textwrap import dedent

class Beer:

    def verse(self, x: int) -> str:
        if x == 0:
            text = """\
            No more bottles of beer on the wall, no more bottles of beer.
            Go to the store and buy some more, 99 bottles of beer on the wall.
            """
        elif x == 1:
            text = """\
            1 bottle of beer on the wall, 1 bottle of beer.
            Take it down and pass it around, no more bottles of beer on the wall.
            """
        elif x == 2:
            text = """\
            2 bottles of beer on the wall, 2 bottles of beer.
            Take one down and pass it around, 1 bottle of beer on the wall.
            """
        else:
            text = """\
            {} bottles of beer on the wall, {} bottles of beer.
            Take one down and pass it around, {} bottles of beer on the wall.
            """.format(x, x, x-1)
        return dedent(text)

    def sing(self, up: int, down: int = 0) -> str:
        return '\n'.join(self.verse(i) for i in range(up, down-1, -1)) + '\n'
