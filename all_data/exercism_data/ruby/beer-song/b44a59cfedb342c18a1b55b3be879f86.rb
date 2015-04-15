class Beer
  def verse(number)
    return last_verse if number == 0
    "#{number} #{bottles(number)} of beer on the wall, #{number} #{bottles(number)} of beer.\nTake #{pronoun(number)} down and pass it around, #{beers_remaining(number)} of beer on the wall.\n"
  end

  def sing(song_start, song_end=0)
    song = []
    song_start.downto(song_end).each do |number|
      song << verse(number)
      song << "\n"
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
