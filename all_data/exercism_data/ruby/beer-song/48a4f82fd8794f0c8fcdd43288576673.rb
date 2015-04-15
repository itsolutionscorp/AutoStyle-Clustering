class BeerSong
  FIRST_VERSE = "bottles of beer on the wall"
  SECOND_VERSE = ".\nTake one down and pass it around, "
  NO_MORE = ".\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"

  def verse number
    song(number)
  end

  def verses number, final
    [*final..number].reverse.map { |v| verse(v) + "\n" }.join
  end

  def sing
    verses(99, 0)
  end

  private

  def song number
    case number
    when 0
      no_more_bottles
    when 1
      last_bottle
    when 2
      first_verse(number) + second_verse(number-1).gsub("bottles", "bottle")
    else
      first_verse(number) + second_verse(number-1)
    end
  end

  def first_verse number
    "#{number} " + FIRST_VERSE + ", #{number} " + FIRST_VERSE[0..14]
  end

  def second_verse number
    SECOND_VERSE + "#{number} " + FIRST_VERSE + ".\n"
  end

  def last_bottle
    first_verse(1).gsub("bottles", "bottle") + second_verse(0).gsub("one", "it").gsub("0", "no more")
  end

  def no_more_bottles
    first_verse("no more").sub("n", "N") + NO_MORE
  end
end
