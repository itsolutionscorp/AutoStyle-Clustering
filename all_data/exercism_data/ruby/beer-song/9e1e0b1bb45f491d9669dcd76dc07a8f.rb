class BeerSong
  def verse (n)
    line1 = "#{bottles(n).capitalize} on the wall, #{bottles(n)}.\n"
    line1 + line2(n)
  end
  def line2 (n)
    case n
    when 0 then "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    when 1 then "Take it down and pass it around, no more bottles of beer on the wall.\n"
    else "Take one down and pass it around, #{bottles(n-1)} on the wall.\n"
    end
  end
  def bottles (n)
    case n
    when 0 then "no more bottles"
    when 1 then "1 bottle"
    else "#{n} bottles"
    end + " of beer"
  end
  def verses (n, m)
    n.downto(m).map{|i|
      verse(i) + "\n"
    }.join
  end
  def sing
    verses(99, 0)
  end
end
