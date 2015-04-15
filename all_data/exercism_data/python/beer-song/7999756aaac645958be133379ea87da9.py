class Beer(object):

    @staticmethod
    def verse(number):
        if number == 2:
            line = (
                '2 bottles of beer on the wall, 2 bottles of beer.\n'
                'Take one down and pass it around, 1 bottle of beer on the wall.\n'
            )
        elif number == 1:
            line = (
                '1 bottle of beer on the wall, 1 bottle of beer.\n'
                'Take it down and pass it around, no more bottles of beer on the wall.\n'
            )
        elif number == 0:
            line = (
                'No more bottles of beer on the wall, no more bottles of beer.\n'
                'Go to the store and buy some more, 99 bottles of beer on the wall.\n'
            )
        else:
            line = (
                '%d bottles of beer on the wall, %d bottles of beer.\n'
                'Take one down and pass it around, %d bottles of beer on the wall.\n'
            ) % (number, number, number - 1)
        return line

    @staticmethod
    def sing(first, last=0):
        verse_numbers = reversed(range(last, first + 1))
        verses = [Beer.verse(number) for number in verse_numbers]
        return '\n'.join(verses) + '\n'
