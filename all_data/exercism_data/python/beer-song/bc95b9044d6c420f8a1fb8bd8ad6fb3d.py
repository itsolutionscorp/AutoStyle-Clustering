class Beer:
    def verse(self, n):
        if n == 0:
            return self.verse_0
        elif n == 1:
            return self.verse_1
        elif n == 2:
            return self.verse_2
        else:
            return ("{} bottles of beer on the wall, {} bottles of beer.\n"
            "Take one down and pass it around, {} bottles of beer on the wall.\n").format(n, n, n - 1)

    def sing(self, begin, end=0):
        return "".join([self.verse(n) + "\n" for n in range(begin, end - 1, -1)])

    verse_0 = ("No more bottles of beer on the wall, no more bottles of beer.\n"
                "Go to the store and buy some more, 99 bottles of beer on the wall.\n")

    verse_1 = ("1 bottle of beer on the wall, 1 bottle of beer.\n"
                "Take it down and pass it around, no more bottles of beer on the wall.\n")

    verse_2 = ("2 bottles of beer on the wall, 2 bottles of beer.\n"
                "Take one down and pass it around, 1 bottle of beer on the wall.\n")
