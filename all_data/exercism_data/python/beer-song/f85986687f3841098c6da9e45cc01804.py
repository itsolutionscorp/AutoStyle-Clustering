class Beer:
    versePattern = ( "%(ThisBottle)s on the wall, %(thisBottle)s.\n"
                     "%(action)s, %(nextBottle)s on the wall.\n" )

    def sing(self, begin, end = 0):
        '''Sing verses counting down from 'begin' to 'end'.'''
        return "\n".join([ self.verse(n)
                           for n in range(begin, end-1, -1) ]) + "\n"

    def verse(self, thisNumber):
        '''Sing one verse.'''        
        return Beer.versePattern % {
            "ThisBottle": Beer.countBottles(thisNumber).capitalize(),
            "thisBottle": Beer.countBottles(thisNumber),
            "nextBottle": Beer.countBottles((thisNumber - 1) % 100),
            "action": Beer.pickAction(thisNumber)
        }

    @staticmethod
    def countBottles(number):
        '''Construct the description for this number of bottles.'''
        if number == 0:
            number = "no more"
        return "%s bottle%s of beer" % (number, "s"[number==1:])

    @staticmethod
    def pickAction(number):
        '''Take appropriate action based on the bottles found.'''
        if number == 0:
            return "Go to the store and buy some more"
        if number == 1:
            return "Take it down and pass it around"
        return "Take one down and pass it around"

    pass
