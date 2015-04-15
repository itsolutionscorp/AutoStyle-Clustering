class Beer:

    def verse(self, verse):
        if (verse >= 3):
            return str(verse) + " bottles of beer on the wall, " + str(verse) + " bottles of beer.\nTake one down and pass it around, " + str((verse - 1)) + " bottles of beer on the wall.\n"
        elif (verse == 2):
            return "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
        elif (verse == 1):
            return "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
        else:
            return "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"

    def sing(self, start, end = 0):
        final = ''
        for number in range(start, (end-1), -1):
            final = final + self.verse(number) + '\n'
        return final
