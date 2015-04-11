class Beer(object):

  def verse(self, verse_num):
    if verse_num > 2:
      return ('{0} bottles of beer on the wall, {0} bottles of beer.\n'
      'Take one down and pass it around, {1} bottles of beer on the wall.\n').format(verse_num, verse_num - 1)
    elif verse_num == 2:
      return ('{0} bottles of beer on the wall, {0} bottles of beer.\n'
      'Take one down and pass it around, {1} bottle of beer on the wall.\n').format(verse_num, verse_num - 1)
    elif verse_num == 1:
      return '1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n'
    else:
      return 'No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n'

    
  def sing(self, high_verse, low_verse = 0):
    verses = range(low_verse, high_verse + 1)
    
    verses.reverse()

    verses = map(self.verse, verses)

    return reduce(lambda a, b: a + '\n' + b, verses) + "\n"
    
