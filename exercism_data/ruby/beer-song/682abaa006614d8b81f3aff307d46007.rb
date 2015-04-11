class BeerSong
  def initialize
  end

  def verse(verse_num)
    case verse_num
    when 2
      "2 bottles of beer on the wall, 2 bottles of beer.\n" \
        "Take one down and pass it around, 1 bottle of beer on the wall.\n"
    when 1
      "1 bottle of beer on the wall, 1 bottle of beer.\n" \
        "Take it down and pass it around, no more bottles of beer on the wall.\n"
    when 0
      "No more bottles of beer on the wall, no more bottles of beer.\n" \
      "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    else
    "#{verse_num} bottles of beer on the wall, #{verse_num} bottles of beer.
Take one down and pass it around, #{verse_num - 1} bottles of beer on the wall.
"
    end
  end

  def verses(verse_start, verse_end)
    verse_start.downto(verse_end).map do |verse_num|
      verse(verse_num)
    end.join("\n") << ("\n")
  end

  def sing
    verses(99, 0)
  end
end
