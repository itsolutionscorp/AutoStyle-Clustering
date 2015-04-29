class BeerSong

  WALL_TEXT = " of beer on the wall"

  def verse(iteration)

    verse_phrases = phrase(iteration)
    verse_phrases[0].capitalize + WALL_TEXT + ", " + verse_phrases[0] + verse_phrases[1] + phrase(iteration - 1)[0] + WALL_TEXT + ".\n"

  end

  def verses(start,stop)
    start.downto(stop).each_with_object("") { |stanza, song| song << (verse(stanza) + "\n") }
  end

  def sing
    verses(99,0)
  end

  private

  def phrase(bottles)
    case bottles
      when 0
        ["no more bottles", " of beer.\nGo to the store and buy some more, "]
      when 1
        ["1 bottle", " of beer.\nTake it down and pass it around, "]
      when -1
        ["99 bottles", ""]
      else
        [bottles.to_s + " bottles", " of beer.\nTake one down and pass it around, "]
    end
  end

end
