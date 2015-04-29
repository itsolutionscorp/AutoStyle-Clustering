class BeerSong
  def sing
    verses(99, 0)
  end

  def verses(starting, ending)
    starting.downto(ending).map { |num_bottles| "#{verse(num_bottles)}\n" }.join
  end

  def verse(num_bottles)
    first_line(num_bottles) + second_line(num_bottles)
  end

  private

  def first_line(num_bottles)
    "#{bottle_amount(num_bottles).capitalize} of beer on the wall, " \
      "#{bottle_amount(num_bottles)} of beer.\n"
  end

  def second_line(num_bottles)
    first_part = if num_bottles == 0
      new_num_bottles = 99
      'Go to the store and buy some more'
    else
      new_num_bottles = num_bottles - 1
      "Take #{bottle_pronoun(num_bottles)} down and pass it around"
    end

    "#{first_part}, #{bottle_amount(new_num_bottles)} of beer on the wall.\n"
  end

  def bottle_amount(num_bottles)
    case num_bottles
    when 1 then '1 bottle'
    when 0 then 'no more bottles'
    else
      "#{num_bottles} bottles"
    end
  end

  def bottle_pronoun(num_bottles)
    if num_bottles == 1
      'it'
    else
      'one'
    end
  end
end
