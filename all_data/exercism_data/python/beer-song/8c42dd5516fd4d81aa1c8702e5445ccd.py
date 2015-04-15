class Beer:
    def verse(self, n):
        if n > 2:
            return self._first_line(n) + self._normal_verse(n)
        elif n == 2:
            return self._first_line(n) + self._verse_two()
        elif n == 1:
            return self._verse_one()
        elif n == 0:
            return self._last_verse()

    def _first_line(self, n):
        n = str(n)
        return n + " bottles of beer on the wall, " + n + " bottles of beer.\n"

    def _normal_verse(self, n):
        return "Take one down and pass it around, " + str(n - 1) + " bottles of beer on the wall.\n"

    def _verse_two(self):
        return "Take one down and pass it around, 1 bottle of beer on the wall.\n"

    def _verse_one(self):
        return "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"

    def _last_verse(self):
        return "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"

    def sing(self, start = -1, end = 0):
        verses = list([self.verse(n) for n in range(end, start + 1)])
        return str.join("\n", list(reversed(verses))) + "\n"
