class BeerSong
  NON_TYPICAL_VERSES = [
    "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n",
    "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n",
    "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
  ]

  def verse(num)
    num > 2 ? typical_verse(num) : NON_TYPICAL_VERSES[num]
  end
  
  def verses(from, to)
    from.downto(to).map{|num| verse(num)}.join("\n") << "\n"
  end
  
  def sing
    verses(99, 0)
  end
  private

  def typical_verse(num)
    "#{num} bottles of beer on the wall, #{num} bottles of beer.\nTake one down and pass it around, #{num-1} bottles of beer on the wall.\n"
  end
end
