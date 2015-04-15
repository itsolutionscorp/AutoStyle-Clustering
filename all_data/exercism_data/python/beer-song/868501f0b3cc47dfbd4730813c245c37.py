# Beer!

class Beer:

    VERSE = """{0} bottle{1} of beer on the wall, {0} bottle{1} of beer.
Take {2} down and pass it around, {3} of beer on the wall.
"""

    LAST_VERSE = """No more bottles of beer on the wall, no more bottles of beer.
Go to the store and buy some more, 99 bottles of beer on the wall.
"""

    def verse(self, num):
        first_plural = ''
        beer_self = 'one'
        if num == 0:
            return self.LAST_VERSE
        if num > 1:
            first_plural = 's'
        else:
            beer_self = 'it'
        second_num = num - 1
        if second_num > 1:
            last_phrase = "{0} bottles".format(second_num)
        elif second_num == 1:
            last_phrase = "1 bottle"
        else:
            last_phrase = "no more bottles"
        return self.VERSE.format(num, first_plural, beer_self, last_phrase)

    def sing(self, start, stop = 0):
        song = ""
        num = start
        while num >= 0:
            song += self.verse(num)
            song += "\n"
            if num == stop: break
            num -= 1
        return song
