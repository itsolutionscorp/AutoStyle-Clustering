class Beer
  def sing(max, min=0)
    range(max, min).collect {|q| verse(q) + "\n"}.join("")
  end

  def verse(quantity)
    "#{bottles(quantity).capitalize} of beer on the wall, #{bottles(quantity)} of beer.\n#{action_with(quantity)}"
  end

  def action_with(quantity)
    unless quantity == 0
      "Take #{wall(quantity)} down and pass it around, #{bottles(quantity - 1)} of beer on the wall.\n"
    else
      "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    end  
  end

  def bottles(quantity)
    if quantity > 1
      "#{quantity} bottles"
    elsif quantity == 1
      "#{quantity} bottle"
    elsif quantity == 0
      "no more bottles"
    end
  end

  def range(max, min)
    (min..max).to_a.reverse
  end

  def wall(quantity)
    quantity == 1 ? "it" : "one"
  end

end
