class Beer
  VERSE_0 = "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
  VERSE_1 = "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
  VERSE_2 = "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
  
  def verse(number)
    case number
    when 0 then VERSE_0
    when 1 then VERSE_1
    when 2 then VERSE_2
    else "#{number} bottles of beer on the wall, #{number} bottles of beer.\nTake one down and pass it around, #{number - 1} bottles of beer on the wall.\n"
    end
  end

  def sing(start, finish = 0)
    start.downto(finish).collect do |i|
      "#{verse(i)}\n"
    end.join
  end 
end
