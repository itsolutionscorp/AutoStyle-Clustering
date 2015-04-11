class Beer:

    def sing(self, start_quantity, end_quantity=0):
        song = ''
        for quantity in reversed(range(end_quantity, start_quantity + 1)):
            song += self.verse(quantity) + "\n"
        return song

    def verse(self, quantity):

        if quantity > 1:
            part = "%d bottles" % quantity
        elif quantity > 0:
            part = "%d bottle" % quantity
        else:
            part = "no more bottles"

        verse = "%s of beer on the wall, %s of beer.\n" % (part[:1].upper() + part[1:], part)

        rest = quantity - 1
        if rest < 0:
            verse += "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
        else:
            if rest > 1:
                part = "one"
                part_two =  "%d bottles" % rest
            elif rest > 0:
                part = "one"
                part_two = "%d bottle" % rest
            elif rest == 0:
                part = "it"
                part_two = "no more bottles"
            verse += "Take %s down and pass it around, %s of beer on the wall.\n" % (part, part_two)

        return verse
