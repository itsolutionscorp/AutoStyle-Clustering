class BeerSong

  def verse(value)
    if value > 2
      "#{value} bottles of beer on the wall, #{value} bottles of beer.\nTake one down and pass it around, #{value - 1} bottles of beer on the wall.\n"
    elsif value == 2
      "#{value} bottles of beer on the wall, #{value} bottles of beer.\nTake one down and pass it around, #{value - 1} bottle of beer on the wall.\n"
    elsif  value == 1
      "#{value} bottle of beer on the wall, #{value} bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
    else
      "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    end
  end

  def verses(start, finish)
    song = []
    while start != (finish - 1)
      song << verse(start)
      start -= 1
    end
    song.join("\n") + "\n"
  end

  def sing
    verses(99, 0)
  end
end
