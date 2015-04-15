class Beer
  def verse(number)
    initial_bottles = count_bottles(number )
    remaining_bottles = count_bottles(next_number(number))
    <<-SONG.gsub /^\s+/, ''
      #{initial_bottles.capitalize} on the wall, #{initial_bottles}.
      #{what_to_do(number)}, #{remaining_bottles} on the wall.
    SONG
  end

  def sing(start_number, end_number = 0)
    start_number.downto(end_number).each_with_object('') do |number, song|
      song << verse(number) + "\n"
    end
  end

  private
  def count_bottles(number)
    "#{how_many(number)} #{bottles(number)} of beer"
  end

  def what_to_do(number)
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
