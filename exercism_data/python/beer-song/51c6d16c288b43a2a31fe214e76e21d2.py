class Beer:

    def sing(self, start, end = 0):
        verse = ''
        for number in range(start, end - 1, -1):
            verse += self.verse(number) + "\n"
        return verse

    def verse(self, number):
        if number > 2:
            return self.plural_start(number) + self.plural_finish(number)
        elif number == 2:
            return self.plural_start(number) + self.singular_finish()
        elif number == 1:
            return self.one()
        else:
            return self.zero()

    def plural_start(self, number):
        return str(number) + " bottles of beer on the wall, " + str(number) + " bottles of beer.\nTake one down and pass it around, "

    def plural_finish(self, number):
        return str(number - 1) + " bottles of beer on the wall.\n"

    def singular_finish(self):
        return "1 bottle of beer on the wall.\n"

    def one(self):
        return "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"

    def zero(self):
        return "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
