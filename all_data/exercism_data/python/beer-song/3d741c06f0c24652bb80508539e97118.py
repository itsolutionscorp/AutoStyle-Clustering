class Beer:
    def verse(self, k):
        if k == 0:
            return "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
        if k == 1:
            return "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
        else:
            return "%(num)d bottles of beer on the wall, %(num)d bottles of beer.\nTake one down and pass it around, %(decnum)d bottle%(maybes)s of beer on the wall.\n" % \
                    {'num' : k, 'decnum' : k-1, 'maybes' : ('' if k == 2 else 's')}

    def sing(self, start, end=0):
        return '\n'.join([self.verse(k) for k in xrange(start, end-1, -1)]) + '\n'
