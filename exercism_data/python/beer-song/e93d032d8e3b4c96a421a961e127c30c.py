class Beer:
    def n_bottles(self, n):
        return "{} bottle{} of beer".format(n if n > 0 else "no more",
                                            "s" if n != 1 else "")

    def verse1(self, n):
        return "{0} on the wall, {0}.\n".format(self.n_bottles(n)).capitalize()

    def verse2(self, n):
        if n == 0:
            return "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
        else:
            return "Take {} down and pass it around, {} on the wall.\n".format(
                "one" if n > 1 else "it",
                self.n_bottles(n - 1))

    def verse(self, n):
        return self.verse1(n) + self.verse2(n)

    def sing(self, start, stop=0):
        return "".join(self.verse(i) + "\n" for i in range(start, stop - 1, -1))
