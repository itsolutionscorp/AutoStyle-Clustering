class BeerSong

  def verse(verse_num)
    if verse_num == 0
      "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    elsif verse_num == 1
      "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
    elsif verse_num == 2
      "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
    else
      "#{verse_num} bottles of beer on the wall, #{verse_num} bottles of beer.\nTake one down and pass it around, #{verse_num-1} bottles of beer on the wall.\n"
    end
  end

  def verses(first, last)
    verse_collection = ''
    (last..first).to_a.reverse.each do |verse_num|
      verse_collection << verse(verse_num)
      verse_collection << "\n"
    end
    verse_collection
  end

  def sing 
    verses(99,0)
  end

end
