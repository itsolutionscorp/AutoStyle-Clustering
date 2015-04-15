class Beer
  def sing(max, min=0)
    max.downto(min).map {|q| verse(q) + "\n"}.join
  end

  def verse(q)
    "#{bottles(q).capitalize} of beer on the wall, #{bottles(q)} of beer.\n#{action_with(q)}"
  end

  def action_with(q)
    unless q == 0
      "Take #{unit(q)} down and pass it around, #{bottles(q - 1)} of beer on the wall.\n"
    else
      "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    end  
  end

  def bottles(q)
    if q > 1
      "#{q} bottles"
    elsif q == 1
      "#{q} bottle"
    elsif q == 0
      "no more bottles"
    end
  end

  def unit(q)
    q == 1 ? "it" : "one"
  end

end
