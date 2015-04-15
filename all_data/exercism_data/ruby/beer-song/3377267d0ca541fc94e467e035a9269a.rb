class BeerSong

  def verse(n)
    line_1 = "#{titleize(bottles(n))} of beer on the wall, #{bottles(n)} of beer.\n"
    line_2 = "Take one down and pass it around, #{bottles(n-1)} of beer on the wall.\n"

    # verse 1 and verse 0 have slight variations for line_2
    if n == 1
      line_2.gsub!("one", "it") if n == 1
    elsif n == 0
      line_2 = "Go to the store and buy some more," + line_2.split(',')[1]
    end
    line_1 + line_2
  end

  def verses(start, stop)
    start.downto(stop).map { |i| verse(i) + "\n" }.join
  end

  def sing
    verses(99, 0)
  end

  private

  def bottles(count)
    case 
    when count < 0
      return "99 bottles"
    when count == 0
      return "no more bottles"
    when count == 1
      return "1 bottle"
    when count > 1
      return "#{count} bottles"
    end
  end

  def titleize(word)
    word[0] = word[0].capitalize
    word
  end
end
