class BeerSong
  def verse(num)
    text = if num == 0
      "No more bottles of beer on the wall, no more bottles of beer.\n" +
      "Go to the store and buy some more, 99 bottles"
    elsif num == 1
      "1 bottle of beer on the wall, 1 bottle of beer.\n" +
      "Take it down and pass it around, no more bottles"
    else
      "#{num} bottles of beer on the wall, #{num} bottles of beer.\n" +
      "Take one down and pass it around, #{num - 1} bottle#{'s' if num != 2}"
    end
    text + " of beer on the wall.\n"
  end

  def verses(starting_verse, ending_verse)
    (ending_verse..starting_verse).map { |num|
      verse(num)
    }.reverse.join("\n") + "\n"
  end

  def sing
    verses(99, 0)
  end
end
