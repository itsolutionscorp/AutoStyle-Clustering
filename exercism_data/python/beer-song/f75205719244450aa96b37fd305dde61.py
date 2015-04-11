class Beer(object):
    def bottles(self, n):
        """Return string 'N bottles of beer' for N >= 0."""
        if n > 1:
            return "%d bottles of beer" % n
        elif n == 1:
            return "1 bottle of beer"
        else:
            return "no more bottles of beer"

    def verse(self, n):
        if n == 0:
            return ("No more bottles of beer on the wall, "
                    "no more bottles of beer.\n"
                    "Go to the store and buy some more, "
                    "99 bottles of beer on the wall.\n")
        else:
            it = "it" if n == 1 else "one"
            return ("%s on the wall, %s.\n"
                    "Take %s down and pass it around, %s on the wall.\n" %
                    (self.bottles(n), self.bottles(n), it, self.bottles(n-1)))

    def sing(self, hi, lo=0):
        verses = (self.verse(n) for n in range(hi, lo - 1, -1))
        return "\n".join(verses) + "\n"
