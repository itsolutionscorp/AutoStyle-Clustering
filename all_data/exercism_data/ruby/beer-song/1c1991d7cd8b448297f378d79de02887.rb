class BeerSong
  def verse(number)
    first_line(number) + last_line(number)
  end

  def verses(start, stop)
    start.downto(stop).map { |number| verse(number) }.join("\n") << "\n"
  end

  def sing
    verses 99, 0
  end

  private

  def first_line(number)
    "#{wall_of_beer(number).capitalize}, #{bottle(number)} of beer.\n"
  end

  def last_line(number)
    last_line_left_half(number) + last_line_right_half(number)
  end

  def bottle(number)
    count(number) + " " + (number == 1 ? "bottle" : "bottles")
  end

  def count(number)
    case number
    when 0
      "no more"
    when -1
      "99"
    else
      number.to_s
    end
  end

  def preposition(number)
    number == 1 ? "it" : "one"
  end

  def last_line_left_half(number)
    if number == 0
      "Go to the store and buy some more, "
    else
      "Take #{preposition(number)} down and pass it around, "
    end
  end

  def last_line_right_half(number)
    wall_of_beer(number-1) + ".\n"
  end

  def wall_of_beer(number)
    "#{bottle(number)} of beer on the wall"
  end
end
