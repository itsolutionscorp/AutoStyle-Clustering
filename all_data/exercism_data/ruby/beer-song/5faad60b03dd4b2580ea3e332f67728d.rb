class Beer
  VERSES    = Hash.new do |hash, key|
    hash[key] = "#{key} bottles of beer on the wall, #{key} bottles of beer.\nTake one down and pass it around, #{key - 1} bottles of beer on the wall.\n"
  end
  VERSES[0] = "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
  VERSES[1] = "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
  VERSES[2] = "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
  
  def verse(bottles)
    VERSES[bottles]
  end

  def sing(start_verse, end_verse = 0)
    start_verse.downto(end_verse).map{ |i| verse(i) + "\n" }.join
  end
end
