class Beer(object):
    def verse(self, n):
        """Return lyrics for verse number n"""
        return self._line1(n) + self._line2(n)

    def sing(self, start, stop=0):
        # for n in range(start, stop):
        glue_verses = lambda text, n: self.verse(n) + "\n" + text
        return reduce(glue_verses, range(stop, start+1), "")


    def _line1(self, n):
        bottles = self._bottleize(n)
        return "{0} of beer on the wall, " \
               "{1} of beer.\n".format(bottles.capitalize(), bottles)

    def _line2(self, n):
        bottles = self._bottleize(n-1)
        if n == 0:
            return "Go to the store and buy some more, " \
                   "99 bottles of beer on the wall.\n"
        elif n == 1:
            return "Take it down and pass it around, " \
                   "{} of beer on the wall.\n".format(bottles)
        else:
            return "Take one down and pass it around, " \
                   "{} of beer on the wall.\n".format(bottles)

    def _bottleize(self, n):
        if n == 0:
            return "no more bottles"
        if n == 1:
            return "1 bottle"
        else:
            return "{} bottles".format(n)
