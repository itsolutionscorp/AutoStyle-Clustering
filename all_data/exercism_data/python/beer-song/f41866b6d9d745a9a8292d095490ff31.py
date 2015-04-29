class Beer(object):
    """Represents beer song generator."""
    def verse(self, number):
        """Return 'number'th verse of beer song."""
        self.number = number
        return "%s %s of beer on the wall, %s %s of beer.\n" \
                "%s, %s %s of beer on the wall.\n" % \
               (self._howmany()[0], self._bottles()[0], self._howmany()[1],\
                self._bottles()[0], self._takeorbuy(), self._howmany()[2], self._bottles()[1])

    def sing(self, start, end=0):
        """Return all beer song verses form 'start' to 'end'."""
        ret = ""
        for i in range(start, end-1, -1):
            ret += self.verse(i)
            ret += "\n"
        return ret

    def _howmany(self):
        if self.number == 0:
            return ["No more", "no more", "99"]
        else:
            if self.number == 1:
                last = "no more"
            else:
                last = str(self.number-1)
            return [str(self.number), str(self.number), last]

    def _bottles(self):
        if self.number == 1:
            return ["bottle", "bottles"]
        elif self.number == 2:
            return ["bottles", "bottle"]
        else:
            return ["bottles", "bottles"]

    def _takeorbuy(self):
        if self.number == 0:
            return "Go to the store and buy some more"
        else:
            if self.number == 1:
                word = "it"
            else:
                word = "one"
            return "Take %s down and pass it around" % word
