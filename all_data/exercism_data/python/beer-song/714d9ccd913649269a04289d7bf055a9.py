class Beer(object):

    def verse(self, n):
        if n == 2:
            return "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
        elif n == 1:
            return "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
        elif n == 0:
            return "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
        else:
            return "%d bottles of beer on the wall, %d bottles of beer.\nTake one down and pass it around, %d bottles of beer on the wall.\n" % (n, n, n-1)

    def sing(self, start, end=0):
        verses = [self.verse(x) for x in xrange(start, end-1, -1)]
        return "\n".join(verses)+"\n"
