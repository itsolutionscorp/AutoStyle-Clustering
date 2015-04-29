# beer.py


class Beer(object):
    '''Beer'''

    def verse(self, anzahl):
        '''verse'''
        if anzahl == 0:
            return B0_ON_WALL + Bot0
        elif anzahl == 1:
            return B1_ON_WALL + Bot1
        elif anzahl == 2:
            return Bn_ON_WALL % (2, 2) + Bot2
        else:
            return Bn_ON_WALL % (anzahl, anzahl) + BotN % (anzahl - 1)

    def sing(self, upper, lower=0):
        ''''sing'''
        return "\n".join([self.verse(anzahl)
                          for anzahl in range(upper, lower - 1, -1)])+("\n")


Bn_ON_WALL = "%d bottles of beer on the wall, %d bottles of beer.\n"
B1_ON_WALL = "1 bottle of beer on the wall, 1 bottle of beer.\n"
B0_ON_WALL = "No more bottles of beer on the wall, no more bottles of beer.\n"

BotN = "Take one down and pass it around, %d bottles of beer on the wall.\n"
Bot2 = "Take one down and pass it around, 1 bottle of beer on the wall.\n"
Bot1 = "Take it down and pass it around, no more bottles of beer on the wall.\n"
Bot0 = "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
