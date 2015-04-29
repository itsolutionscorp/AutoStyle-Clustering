class Beer(object):
    # Not a /particularly/ smart solution, but a simple one, and simple is smart
    def verse(self, number):
        if number > 2:
            return "%d bottles of beer on the wall, %d bottles of beer.\nTake one down and pass it around, %d bottles of beer on the wall.\n" %(number, number, number-1)
        elif number == 2:
            return "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
        elif number == 1:
            return "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
        else:
            return "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"

    def sing(self, start, stop=0):
        return "\n".join([self.verse(v) for v in range(start, stop-1, -1)]) + "\n"  # Addtl \n to cover for join()
