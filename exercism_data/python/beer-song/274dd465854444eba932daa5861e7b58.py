class Beer(object):
    def bottles(self, n):
        if n == 0:
            return 'no more bottles of beer'
        elif n == 1:
            return '1 bottle of beer'
        else:
            return '{} bottles of beer'.format(n)

    def verse(self, n):
        verse = '{0} on the wall, {0}.\n'.format(self.bottles(n))
        verse += 'Go to the store and buy some more' if n == 0 else 'Take {} down and pass it around'.format('it' if n == 1 else 'one')
        verse += ', {} on the wall.\n'.format(self.bottles(99 if n == 0 else n - 1))
        return verse[0].upper() + verse[1:]

    def sing(self, start, end=0):
        song = ''
        for v in reversed(range(end, start + 1)):
            song += self.verse(v) + '\n'
        return song
