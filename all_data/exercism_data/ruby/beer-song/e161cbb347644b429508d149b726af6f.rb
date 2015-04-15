class Beer

  def sing(first, last=0)
    s = ""
    first.downto(last).each do |number|
      s << verse(number)
      s << "\n"
    end
    s
  end

  def verse(number)
    s = ""
    s << pluralize_bottles(number).capitalize + " of beer on the wall, "
    s << pluralize_bottles(number) + " of beer.\n"
    s << action(number)
    s << remaining_bottles(number)
  end

private
  def action(number)
    if number == 0
      "Go to the store and buy some more, "
    else
      "Take #{number ==1 ? 'it' : 'one'} down and pass it around, "
    end
  end

  def remaining_bottles(number)
    number = 100 if number == 0
    "#{pluralize_bottles(number-1)} of beer on the wall.\n"
  end


  def pluralize_bottles(number)
    if number == 0
      "no more bottles"
    elsif number == 1
      "1 bottle"
    else
      "#{number} bottles"
    end
  end


end
