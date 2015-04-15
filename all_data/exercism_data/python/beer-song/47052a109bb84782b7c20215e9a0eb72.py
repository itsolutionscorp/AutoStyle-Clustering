class Beer(object):
    """Sing the beer song!"""
    class Lyric(object):
        """Format strings for beer song."""
        BEER = '{quantity} of beer'
        INTRO = '{beer} on the wall, {beer}.\n'
        TURN = 'Take {identity} down and pass it around'
        FINAL_TURN = 'Go to the store and buy some more'
        OUTRO = '{turn}, {beer} on the wall.\n'

    def verse(self, n):
        """Sing verse n of the beer song."""
        beer = self.Lyric.BEER.format(quantity=self._get_quantity(n))
        next_beer = self.Lyric.BEER.format(
            quantity=self._get_quantity((n - 1) % 100))
        identity = 'it' if n == 1 else 'one'
        turn = (self.Lyric.TURN.format(identity=identity)
                if n != 0 else self.Lyric.FINAL_TURN)

        return (self.Lyric.INTRO.format(beer=beer).capitalize() +
                self.Lyric.OUTRO.format(turn=turn, beer=next_beer))

    def sing(self, start, end=0):
        """Sing verses start to end of the beer song.
           Sing all verses from start if only start specified."""
        verses = []
        for n in xrange(start, end - 1, -1):
            verses.append(self.verse(n) + '\n')
        return ''.join(verses)

    def _get_quantity(self, amount):
        """Convert amount to human readable number of bottles."""
        if amount == 0:
            return 'no more bottles'
        if amount == 1:
            return '1 bottle'
        return '{0} bottles'.format(amount)
