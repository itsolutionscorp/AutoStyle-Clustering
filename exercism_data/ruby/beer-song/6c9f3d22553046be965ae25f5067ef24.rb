class BeerSong
  def verse(number)
    "#{bottles(number, :sentence_case)} on the wall, #{bottles(number)}.\n" <<
    "#{action(number)}, #{bottles(number - 1)} on the wall.\n"
  end

  def verses(from, to)
    from.downto(to).map { |number| verse(number) + "\n" }.join
  end

  def sing
    verses(99, 0)
  end

  private

  def bottle_number(number, format = nil)
    case number
    when -1
      99
    when 0
      format == :sentence_case ? "No more" : "no more"
    else
      number
    end
  end

  def bottle_unit(number)
    number == 1 ? "bottle" : "bottles"
  end

  def bottles(number, format = nil)
    "#{bottle_number(number, format)} #{bottle_unit(number)} of beer"
  end

  def pronoun(number)
    number == 1 ? "it" : "one"
  end

  def action(number)
    if number.zero?
      "Go to the store and buy some more"
    else
      "Take #{pronoun(number)} down and pass it around"
    end
  end
end
