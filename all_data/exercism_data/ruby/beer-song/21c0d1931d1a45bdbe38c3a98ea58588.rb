class Beer

  def sing(start_verse, end_verse = 0)
    start_verse.downto(end_verse).inject(""){|song, number| song += verse(number) + "\n"}
  end

  def verse(number)
    line1(number) + line2(number-1)
  end

  def line1(number)
    "#{pluralize(number).capitalize} of beer on the wall, #{pluralize(number)} of beer.\n"
  end

  def line2(number)
    return "Go to the store and buy some more, 99 bottles of beer on the wall.\n" if number < 0
    "Take #{pronoun(number)} down and pass it around, #{pluralize(number)} of beer on the wall.\n"
  end

  def pronoun(number)
    return "one" if number >= 1
    "it"
  end

  def pluralize(number)
    return "#{number} bottles" if number > 1
    return "#{number} bottle" if number == 1
    "no more bottles"
  end
end
