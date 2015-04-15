class Beer:


  def sing(self, top, lower=0):
    verses = [ self.verse(x) for x in reversed(range(lower, top + 1))]
    return "\n".join(verses) + "\n"
    

  def verse(self, count):
    if count == 0: 
      return self.verse_0()
    elif count == 1:
      return self.verse_1()
    elif count == 2:
      return self.verse_2()
    else:
      return self.verse_x(count)

  def verse_0(self):
    return ( "No more bottles of beer on the wall, no more bottles of beer.\n"
             "Go to the store and buy some more, 99 bottles of beer on the wall.\n")

  def verse_1(self):
    return ("1 bottle of beer on the wall, 1 bottle of beer.\n"
            "Take it down and pass it around, no more bottles of beer on the wall.\n")

  def verse_2(self):
    return ( "2 bottles of beer on the wall, 2 bottles of beer.\n"
            "Take one down and pass it around, 1 bottle of beer on the wall.\n")

  def verse_x(self, count):
    return ( str(count) +  " bottles of beer on the wall, " + str(count) + " bottles of beer.\n"
            "Take one down and pass it around, " + str(count - 1) +  " bottles of beer on the wall.\n")
