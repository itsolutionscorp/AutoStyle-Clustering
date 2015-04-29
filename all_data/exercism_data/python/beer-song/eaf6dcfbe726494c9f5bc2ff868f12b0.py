class Beer(object):
    def verse(self, number):
        if number == 0:
            call = 'No more bottles of beer on the wall, no more bottles of beer.\n'
            response = 'Go to the store and buy some more, 99 bottles of beer on the wall.\n'
        elif number == 1:
            call = '{0} bottle of beer on the wall, {0} bottle of beer.\n'
            response = 'Take it down and pass it around, no more bottles of beer on the wall.\n'
        elif number == 2:
            call = '{0} bottles of beer on the wall, {0} bottles of beer.\n'
            response = 'Take one down and pass it around, {0} bottle of beer on the wall.\n'
        else:
            call = '{0} bottles of beer on the wall, {0} bottles of beer.\n'
            response = 'Take one down and pass it around, {0} bottles of beer on the wall.\n'

        return ''.join([call.format(number), response.format(number - 1)])

    def sing(self, begin, end=0):
        return ''.join(self.verse(i) + '\n'
                for i in range(begin, end - 1, -1))
