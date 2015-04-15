class Beer
  def sing(from, to=0)
    from.downto(to).map { |number| verse(number) }.join("\n") + "\n"
  end

  def verse(number)
    lines = ["#{number_of_bottles(number)} of beer on the wall, #{number_of_bottles(number)} of beer.".capitalize]
    lines << if number == 0
      "Go to the store and buy some more, 99 bottles of beer on the wall."
    else
      "Take #{pronoun(number)} down and pass it around, #{number_of_bottles(number - 1)} of beer on the wall."
    end
    lines.join("\n") + "\n"
  end

  private

  def number_of_bottles(number)
    "#{number_phrase(number)} #{bottles(number)}"
  end

  def bottles(number)
    if number == 1
      "bottle"
    else
      "bottles"
    end
  end

  def number_phrase(number)
    if number == 0
      "no more"
    else
      number.to_s
    end
  end

  def pronoun(number)
    if number == 1
      "it"
    else
      "one"
    end
  end
end
