class Beer(object):
    refrain = '{beer} on the wall, {beer}.\n'
    verse_one = 'Take {some} down and pass it around, {beer_left} on the wall.\n'
    verse_end = 'Go to the store and buy some more, {beer_bought} on the wall.\n'
    total_beer_count = 99

    def __n(self, base, singular, plural, value, empty=''):
        return base.format(singular if value == 1 else plural if value > 1 else empty)

    def __beer(self, count):
        return '{} {}'.format(
            count if count > 0 else 'no more',
            self.__n('{} of beer', 'bottle', 'bottles', count, 'bottles')
        )

    def __compose(self, verse, fragments):
        composed = []
        for v in verse:
            verse_sing = v.format(**fragments)
            verse_sing = verse_sing[0].upper()+verse_sing[1:]
            composed.append(verse_sing)
        return ''.join(composed)

    def verse(self, count):
        verse = [self.refrain]
        if count < 1:
            verse.append(self.verse_end)
        else:
            verse.append(self.verse_one)

        fragments = dict(
            beer=self.__beer(count),
            beer_left=self.__beer(count-1),
            some=self.__n('{}', 'it', 'one', count),
            beer_bought=self.__beer(self.total_beer_count),
        )
        return self.__compose(verse, fragments)

    def sing(self, verse_start, verse_stop=0):
        song = []
        for v in reversed(range(verse_stop, verse_start+1)):
            song.append(self.verse(v))
            song.append('\n')
        return ''.join(song)
