class BeerSong

  def verse(number)
    case number
      when ""
        "I need to know how many bottles to sing!"
      when 0
        "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
      when 1
        "#{number} bottle of beer on the wall, #{number} bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
      when 2
        "#{number} bottles of beer on the wall, #{number} bottles of beer.\nTake one down and pass it around, #{number -1} bottle of beer on the wall.\n"
      else
        "#{number} bottles of beer on the wall, #{number} bottles of beer.\nTake one down and pass it around, #{number -1} bottles of beer on the wall.\n"
    end
  end

  def verses(first, last)
    first.downto(last).map { |n| verse n }.join("\n") + "\n"
  end

  def sing
    verses 99, 0
  end
end
