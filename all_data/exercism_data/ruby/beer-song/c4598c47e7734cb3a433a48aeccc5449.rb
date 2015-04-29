class Beer
  def verse(number)
    initial_bottles, what_to_do, remaining_bottles = lyrics(number)
    "#{initial_bottles.capitalize} on the wall, #{initial_bottles}.\n#{what_to_do}, #{remaining_bottles} on the wall.\n"
  end

  def sing(start_number, end_number = 0)
    start_number.downto(end_number).each_with_object('') { |number, song| song << verse(number) + "\n" }
  end

  private
  def lyrics(number)
    [
      count_bottles(number),
      sing_refrain(number),
      count_bottles(next_number(number))
    ]
  end

  def count_bottles(number)
    "#{how_many(number)} #{bottles(number)} of beer"
  end

  def sing_refrain(number)
    if number == 0
      'Go to the store and buy some more'
    else
      "Take #{pronoun(number)} down and pass it around"
    end
  end

  def how_many(number)
    number == 0 ? 'no more' : number
  end

  def bottles(number)
    "bottle#{number != 1 ? 's' : ''}"
  end

  def pronoun(number)
    number == 1 ? 'it' : 'one'
  end

  def next_number(number)
    number == 0 ? 99 : number - 1
  end
end
