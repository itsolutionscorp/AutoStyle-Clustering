class Beer(object):
    VERSE_TEMPLATE = '''
%(current)s of beer on the wall, %(current)s of beer.
Take %(pronoun)s down and pass it around, %(previous)s of beer on the wall.
'''.lstrip()

    LAST_VERSE = '''
No more bottles of beer on the wall, no more bottles of beer.
Go to the store and buy some more, 99 bottles of beer on the wall.
'''.lstrip()

    def bottles(self, n):
        if n > 1:
            return '%s bottles' % n
        if n == 1:
            return '1 bottle'
        return 'no more bottles'

    def verse(self, n):
        if not n:
            return Beer.LAST_VERSE
        return Beer.VERSE_TEMPLATE % {
            'current': self.bottles(n),
            'previous': self.bottles(n - 1),
            'pronoun': 'it' if n == 1 else 'one'
        }

    def sing(self, start, end=0):
        verses = (self.verse(i) for i in range(start, end - 1, -1))
        return '%s\n' % '\n'.join(verses)
