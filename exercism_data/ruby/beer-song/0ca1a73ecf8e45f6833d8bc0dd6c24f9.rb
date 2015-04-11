class BeerSong
  def verse(number)
    if number > 2
      return "#{number} bottles of beer on the wall, #{number} bottles of beer.\n" \
             "Take one down and pass it around, #{number - 1} bottles of beer on the wall.\n"
    end
    if number == 2
      return "2 bottles of beer on the wall, 2 bottles of beer.\n" \
             "Take one down and pass it around, 1 bottle of beer on the wall.\n"
    end
    if number == 1
      return "1 bottle of beer on the wall, 1 bottle of beer.\n" \
             "Take it down and pass it around, no more bottles of beer on the wall.\n"
    end
    if number == 0
      return "No more bottles of beer on the wall, no more bottles of beer.\n" \
             "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    end
  end

  def verses(first, last)
    song = ""
    until first < last
      song += verse(first) + "\n"
      first -= 1
    end
    song
  end

  def sing
    verses(99, 0)
  end
end
