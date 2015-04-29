from string import capitalize


class Beer(object):

    """Beer song generator"""

    def _bottle_phrase(self, number):
        """Return exactly how many bottles are involved."""
        phrase = "bottle"
        if number:
            phrase = "{0} {1}".format(number, phrase)
        else:
            phrase = "no more " + phrase
        if number != 1:
            phrase += "s"
        return phrase

    def _beer_phrase(self, number):
        """Return how much beer is involved."""
        return self._bottle_phrase(number) + " of beer"

    def _wall_phrase(self, number):
        """Return where the beer is located."""
        return self._beer_phrase(number) + " on the wall"

    def _action_phrase(self, number):
        """Return what to do under these circumstances."""
        if number:
            phrase = "Take {0} down and pass it around"
            if number == 1:
                return phrase.format("it")
            else:
                return phrase.format("one")
        else:
            return "Go to the store and buy some more"

    def _previous(self, number):
        """Return number of previous verse."""
        return(number + 99) % 100

    def verse(self, number):
        """Return one verse of the beer song."""
        return (capitalize(self._wall_phrase(number)) + ", " +
                self._beer_phrase(number) + ".\n" +
                self._action_phrase(number) + ", " +
                self._wall_phrase(self._previous(number)) + ".\n")

    def sing(self, start, stop=0):
        """Return several verses of the beer song."""
        return "\n".join([self.verse(n) for
                          n in range(start, stop - 1, -1)]) + "\n"
