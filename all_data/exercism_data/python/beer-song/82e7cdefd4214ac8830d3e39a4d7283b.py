class Beer:
    def _bottles(self, beer_count):
        if beer_count < 0:
            raise ValueError("Beer count `{0}' must be >= 0".format(beer_count))

        if beer_count == 0:
            return 'no more bottles'
        else:
            return '{0} bottle{1}'.format(beer_count, 's' if beer_count > 1 else '')

    def verse(self, verse_number):
        if verse_number < 0:
            raise ValueError("Verse number `{0}' must be >= 0".format(verse_number))

        stanza = "{0} of beer on the wall, {0} of beer.\n".format(self._bottles(verse_number)).capitalize()

        if verse_number == 0:
            stanza += "Go to the store and buy some more, {0} of beer on the wall.\n".format(self._bottles(99))
        else:
            stanza += "Take {0} down and pass it around, {1} of beer on the wall.\n".format("one" if verse_number > 1 else "it", self._bottles(verse_number - 1))

        return stanza

    def sing(self, starting_verse, ending_verse=0):
        if ending_verse > starting_verse:
            raise ValueError("starting verse `{0}' must be >= ending verse `{1}'".format(starting_verse, ending_verse))

        return "\n".join(map(self.verse, range(starting_verse, ending_verse - 1, -1))) + "\n"
