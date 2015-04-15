FIRST_LINE = "{0} bottle{1} of beer on the wall, {0} bottle{1} of beer.\n"
SECOND_LINE = "Take {} down and pass it around, " + \
              "{} bottle{} of beer on the wall.\n"
ENDING_FIRST_LINE = "No more bottles of beer on the wall," + \
                    " no more bottles of beer.\n"
ENDING_SECOND_LINE = "Go to the store and buy some more," + \
                     " 99 bottles of beer on the wall.\n"


class Beer:

    def verse(self, number):
        if number == 1:
            return FIRST_LINE.format(1, '') +\
                SECOND_LINE.format('it', 'no more', 's')
        elif number == 0:
            return ENDING_FIRST_LINE + ENDING_SECOND_LINE
        elif number == 2:
            return FIRST_LINE.format(number, 's') +\
                SECOND_LINE.format('one', number-1, '')
        else:
            return FIRST_LINE.format(number, 's') +\
                SECOND_LINE.format('one', number-1, 's')

    def sing(self, start, end=0):
        song = ''
        for i in range(end, start+1)[::-1]:
            song += self.verse(i)
            song += '\n'
        return song
