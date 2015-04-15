class Beer
  def verse(number)
    number == 0 ? last_verse : normal_verse(number)
  end

  def normal_verse(number)
    "#{number} #{bottles(number)} of beer on the wall, #{number} #{bottles(number)} of beer.\nTake #{pronoun(number)} down and pass it around, #{beers_remaining(number)} of beer on the wall.\n"
  end

  def sing(start, stop=0)
    song = []
    start.downto(stop).each do |number|
      song << verse(number) + "\n"
    end
    song.join("")
  end

  def last_verse
    "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
  end

  def pronoun(number)
    number == 1 ? "it" : "one"
  end

  def bottles(count)
    count < 2 ? "bottle" : "bottles"
  end

  def beers_remaining(count)
    return "no more bottles" if count < 2
    return "1 bottle" if count == 2
    "#{count - 1} bottles"
  end

end
