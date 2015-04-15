class Beer:

    def sing(self, start_quantity, end_quantity=0):
        full = ''
        for quantity in reversed(range(end_quantity, start_quantity + 1)):
            full += self.verse(quantity) + "\n"
        return full

    def verse(self, quantity):

        if quantity > 1:
            verse = str(quantity) + " bottles of beer on the wall, " + str(quantity) + " bottles of beer.\n"
        elif quantity > 0:
            verse = str(quantity) + " bottle of beer on the wall, " + str(quantity) + " bottle of beer.\n"
        else:
            verse = "No more bottles of beer on the wall, no more bottles of beer.\n"

        rest = quantity - 1
        if rest > 1:
            verse += "Take one down and pass it around, " + str(rest) + " bottles of beer on the wall.\n"
        elif rest > 0:
            verse += "Take one down and pass it around, " + str(rest) + " bottle of beer on the wall.\n"
        elif rest == 0:
            verse += "Take it down and pass it around, no more bottles of beer on the wall.\n"
        else:
            verse += "Go to the store and buy some more, 99 bottles of beer on the wall.\n"

        return verse
