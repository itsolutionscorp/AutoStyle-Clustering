class Beer
  # I call this: Attack of the Ternaries. Sounds kind of like a Jurassic Park sequel, no?

  def verse(number)
    bottle_count, what_to_do, remaining_bottles = lyrics(number)

    "#{bottle_count.capitalize} on the wall, #{bottle_count}.\n#{what_to_do}, #{remaining_bottles} on the wall.\n"
  end

  def sing(start_number, end_number = 0)
    start_number.downto(end_number).each_with_object('') { |number, song| song << verse(number) + "\n" }
  end

  private
  def lyrics(number)
    [
      pluralize_bottles(number),
      refrain(number),
      pluralize_bottles(next_number(number))
    ]
  end

  def pluralize_bottles(number)
    "#{number == 0 ? 'no more' : number} bottle#{number != 1 ? 's' : ''} of beer"
  end

  def refrain(number)
    number == 0 ? 'Go to the store and buy some more' : "Take #{number == 1 ? 'it' : 'one'} down and pass it around"
  end

  def next_number(number)
    number == 0 ? 99 : number - 1
  end
end
