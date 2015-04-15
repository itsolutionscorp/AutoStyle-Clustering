__author__ = 'angelo'


class Beer():
    def verse(self, x):
        verse = ''
        verse += "{0} {1} of beer on the wall, {0} {1} of beer.\n".format(x if x > 0 else "no more",
                                                                          "bottle" if x == 1 else "bottles").capitalize()
        if x == 1:
            verse += "Take it down and pass it around, no more bottles of beer on the wall.\n"
        elif x == 0:
            verse += "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
        else:
            verse += "Take one down and pass it around, {0} {1} of beer on the wall.\n".format(x - 1,
                                                                                               "bottle" if x == 2 else "bottles")
        return verse

    def sing(self, start, end=None):
        end = end if end else 0
        return '\n'.join([self.verse(x) for x in range(start, end - 1, -1)]) + '\n'
