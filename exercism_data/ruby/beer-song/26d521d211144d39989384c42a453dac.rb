class BeerSong
  def verse(num)
    case num
    when 0 then "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    else "#{bottle(num)} of beer on the wall, #{bottle(num)} of beer.\nTake #{take(num)} down and pass it around, #{bottle(num-1)} of beer on the wall.\n"
    end
  end

  def bottle(num)
    case num
    when 0 then "no more bottles"
    when 1 then "1 bottle"
    else "#{num} bottles"
    end
  end

  def take(num)
    return "it" if num == 1
    return "one"
  end

  def verses(first, last)
    first.downto(last).map { |num| verse(num) + "\n" }.join
  end

  def sing
    verses(99, 0)
  end

end
