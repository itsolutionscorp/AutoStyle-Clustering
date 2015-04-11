class Beer(object):
  
    zero_bottles = "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n" 
    one_bottle   = "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
    two_bottles  = "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"

    responses = {
        0: zero_bottles,
        1: one_bottle,
        2: two_bottles
    }

    def verse(self, num):
        return self.responses.get(num, self._multiple_bottles(num))

    def sing(self, start, end = 0):
        verses_numbers = range(start, end - 1, -1)
        verses = [self.verse(verse_number) for verse_number in verses_numbers]
        return "\n".join(verses) + "\n"

    def _multiple_bottles(self, amount):
        verses = "%s bottles of beer on the wall, %s bottles of beer.\n" % (amount, amount)
        verses += "Take one down and pass it around, %s bottles of beer on the wall.\n" % (amount -1)
        return verses
