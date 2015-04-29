verse = """{0} bottles of beer on the wall, {0} bottles of beer.
Take one down and pass it around, {1} bottle{2} of beer on the wall.
"""

secondlast = """1 bottle of beer on the wall, 1 bottle of beer.
Take it down and pass it around, no more bottles of beer on the wall.
"""

last = """No more bottles of beer on the wall, no more bottles of beer.
Go to the store and buy some more, 99 bottles of beer on the wall.
"""

class Beer(object):
    def verse(self, i):
        if i == 0:
            return last
        if i == 1:
            return secondlast
        if i == 2:
            return verse.format(i, i - 1, '')
        return verse.format(i, i - 1, 's')


    def sing(self, i, j=0):
        song = ""
        for x in range(i, j - 1, -1):
            if x == 0:
                song += last
            elif x == 1:
                song += secondlast
            elif x == 2:
                song += verse.format(x, x - 1, '')
            else:
                song += verse.format(x, x - 1, 's')
            song += '\n'
        return song
