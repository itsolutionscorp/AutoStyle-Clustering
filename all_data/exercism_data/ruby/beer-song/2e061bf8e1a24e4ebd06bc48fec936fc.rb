class BeerSong

  def verse n
    line1(n) + line2(n)
  end

  def verses from, to
    from.downto(to).map { |v| verse(v) + "\n" }.join
  end

  def sing
    verses 99, 0
  end

  def line1 n
    "#{bottles(n).capitalize} of beer on the wall, #{bottles(n)} of beer.\n"
  end

  def line2 n
    second_half = ", #{bottles(n - 1)} of beer on the wall.\n"
    if n == 1
      "Take it down and pass it around" + second_half
    elsif n == 0
      "Go to the store and buy some more"  + second_half
    else
      "Take one down and pass it around" + second_half
    end
  end

  def bottles n
    return "1 bottle"        if n == 1
    return "no more bottles" if n == 0
    return "99 bottles"      if n < 0
    "#{n} bottles"
  end
end
