class BeerSong
  def verse(num)
    "#{current_bottles(num)}" \
    " of beer on the wall, " \
    "#{(current_bottles(num)).downcase} of beer.\n" \
    "#{second_phrase(num)}" \
    "#{remaining_bottles(num)} of beer on the wall.\n"
  end

  def verses(num1, num2)
    output = ""
    i = num1
    while i >= num2
      output << "#{verse(i)}\n"
      i -= 1
    end
    output
  end

  def sing
    verses(99, 0)
  end

  private

  def current_bottles(num)
    case(num)
    when 1
      "#{num}" + " bottle"
    when 0
      "No more bottles"
    else
      "#{num}" + " bottles"
    end
  end

  def second_phrase(num)
    case(num)
    when 1
      "Take it down and pass it around, "
    when 0
      "Go to the store and buy some more, "
    else
      "Take one down and pass it around, "
    end
  end

  def remaining_bottles(num)
    case(num)
    when 2
      "#{num-1}" + " bottle"
    when 1
      "no more bottles"
    when 0
      "99 bottles"
    else
      "#{num-1}" + " bottles"
    end
  end
end
