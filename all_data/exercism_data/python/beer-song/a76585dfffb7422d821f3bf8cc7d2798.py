SPECIAL_VERSES = {
    0:"""No more bottles of beer on the wall, no more bottles of beer.
Go to the store and buy some more, 99 bottles of beer on the wall.
""",

    1:"""1 bottle of beer on the wall, 1 bottle of beer.
Take it down and pass it around, no more bottles of beer on the wall.
""",

    2:"""2 bottles of beer on the wall, 2 bottles of beer.
Take one down and pass it around, 1 bottle of beer on the wall.
"""
}

VERSE_TEMPLATE = \
"""{current} bottles of beer on the wall, {current} bottles of beer.
Take one down and pass it around, {next} bottles of beer on the wall.
"""

class Beer(object):
    """Sings the beer song"""

    def verse(self, verse_number):
        """Gives a string of the specified verse of 'The Beer Song'"""
        if verse_number in SPECIAL_VERSES:
            return SPECIAL_VERSES[verse_number]
        else:
            return VERSE_TEMPLATE.format(
                        current = verse_number,
                        next = verse_number-1)

    def sing(self, start_verse, end_verse = 0):
        """Gives specific verses of 'The Beer Song'"""
        count_direction = -1 # count down to zero
        verse_numbers = range(start_verse,end_verse-1,count_direction)
        verses = [self.verse(verse_number)+'\n'
                        for verse_number in verse_numbers]
        return ''.join(verses)
