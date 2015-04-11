class BeerSong

  def verse(n)
    return VERSE_ZERO if n == 0
    "#{bottles(n)} of beer on the wall, #{bottles(n)} of beer.\n" \
    "Take #{one_or_it(n)} down and pass it around, #{bottles(n-1)} of beer on the wall.\n"
  end

  def bottles(n)
    case n
    when 2..99 then "#{n} bottles"
    when 1 then "1 bottle"
    when 0 then "no more bottles"
    end
  end

  def one_or_it(n)
    n > 1 ? "one" : "it"
  end

  def verses(a, b)
    a.downto(b).map { |n| verse(n) }.join("\n") + "\n"
  end

  def sing
    verses(99, 0)
  end

  VERSE_ZERO = "No more bottles of beer on the wall, no more bottles of beer.\n" \
  "Go to the store and buy some more, 99 bottles of beer on the wall.\n"

end
