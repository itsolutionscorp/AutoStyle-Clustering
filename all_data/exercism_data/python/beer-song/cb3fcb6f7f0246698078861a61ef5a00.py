class Beer:
    """ Counts down the bottles of beer on the wall song"""
    def verse(self, n):
        if n == 0:
            return  "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
        b = "bottles"
        c = n - 1
        d = "one"
        if n == 1:
            b = "bottle"
            c = "no more"
            d = "it"
        line1 = "{} {} of beer on the wall, {} {} of beer.\n".format(n,b, n,b)
        line2 = "Take {} down and pass it around, {} bottles of beer on the wall.\n".format(d, c)
        if n == 2:
            line2 = "Take {} down and pass it around, {} bottle of beer on the wall.\n".format(d, c)
        return line1 + line2

    def sing(self, start, end = 0):
        result = ""
        for i in range(start, end - 1, -1):
            result = result + self.verse(i) + "\n"
        return result
