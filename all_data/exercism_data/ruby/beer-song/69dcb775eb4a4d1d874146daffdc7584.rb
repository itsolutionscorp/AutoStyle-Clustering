class Beer

  def verse(bottles_num)
    case bottles_num
    when 2
      "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
    when 1
      "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
    when 0
      "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    else
      "#{bottles_num} bottles of beer on the wall, #{bottles_num} bottles of beer.\nTake one down and pass it around, #{bottles_num - 1} bottles of beer on the wall.\n"
    end
  end

  def sing(starting_verse, ending_verse = 0)
    starting_verse.downto(ending_verse).map do |bottles_num|
      verse(bottles_num) + "\n"
    end.join
  end

end
