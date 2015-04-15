class Beer(object):
  
    def verse(self, num):
        if num == 0:
            return self._zero_bottles()
        if num == 1:
            return self._one_bottle()
        if num == 2:
            return self._two_bottles()
        return self._multiple_bottles(num)

    def sing(self, start, end = 0):
        verses_numbers = range(start, end - 1, -1)
        verses = [self.verse(verse_number) for verse_number in verses_numbers]
        return "\n".join(verses) + "\n"

    def _zero_bottles(self):
        verses = "No more bottles of beer on the wall, no more bottles of beer.\n" 
        verses += "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
        return verses

    def _one_bottle(self):
        verses = "1 bottle of beer on the wall, 1 bottle of beer.\n"
        verses += "Take it down and pass it around, no more bottles of beer on the wall.\n"
        return verses

    def  _two_bottles(self):
        verses = "2 bottles of beer on the wall, 2 bottles of beer.\n"
        verses += "Take one down and pass it around, 1 bottle of beer on the wall.\n"
        return verses

    def _multiple_bottles(self, amount):
        verses = "%s bottles of beer on the wall, %s bottles of beer.\n" % (amount, amount)
        verses += "Take one down and pass it around, %s bottles of beer on the wall.\n" % (amount -1)
        return verses
