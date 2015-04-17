class Beer(object):
    def verse(self, n):
        """Return lyrics for verse for bottle number n"""
        return self._line1(n) + self._line2(n)

    def sing(self, start, stop=0):
        """Return lyrics for verses for bottle numbers from start to stop,
        concatenated with \\n"""
        return "\n".join(
            self.verse(n) for n in range(start, stop-1, -1)
        ) + "\n"


    def _line1(self, n):
        """Return 1st line for verse n"""
        bottles = self._bottleize(n)
        return "{0} of beer on the wall, " \
               "{1} of beer.\n".format(bottles.capitalize(), bottles)

    def _line2(self, n):
        """Return 2nd line for a verse n"""
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
        """Convert number of bottles to text for a verse line"""
        if n == 0:
            return "no more bottles"
        if n == 1:
            return "1 bottle"
        else:
            return "{} bottles".format(n)