class BeerSong
  def verse(number)
    case number
    when 0
      "No more bottles of beer on the wall, #{bottles(number)}.\nGo to the store and buy some more, #{bottles(99)} on the wall.\n"
    else
      "#{bottles(number)} on the wall, #{bottles(number)}.\nTake #{pronoun(number)} down and pass it around, #{bottles(number - 1)} on the wall.\n"
    end
  end

  def verses(from, to)
    from.downto(to).map { |number| verse(number) + "\n" }.join
  end

  def sing
    verses(99, 0)
  end

  private

  def bottles(number)
    unit = case number
    when 0
      "no more bottles"
    when 1
      "1 bottle"
    else
      "#{number} bottles"
    end
    "#{unit} of beer"
  end

  def pronoun(number)
    number == 1 ? "it" : "one"
  end
end
