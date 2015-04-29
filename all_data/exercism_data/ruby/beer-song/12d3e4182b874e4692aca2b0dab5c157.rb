class BeerSong

  def verse(n)
    line1(n) + line2(n)
  end

  def verses(start, stop)
    start.downto(stop).map { |i| verse(i) + "\n" }.join
  end

  def sing
    verses(99, 0)
  end

  private

  def line1(n)
    "#{bottles(n).capitalize} of beer on the wall, #{bottles(n)} of beer.\n"
  end

  def line2(n)
    line = "Take one down and pass it around, #{bottles(n-1)} of beer on the wall.\n"
    if n == 1
      line.gsub!("one", "it")
    elsif n == 0
      line = "Go to the store and buy some more," + line.split(',')[1]
    end
    line
  end

  def bottles(count)
    case 
      when count  < 0 then "99 bottles"
      when count == 0 then "no more bottles"
      when count == 1 then "1 bottle"
      when count  > 1 then "#{count} bottles"
    end
  end
end
