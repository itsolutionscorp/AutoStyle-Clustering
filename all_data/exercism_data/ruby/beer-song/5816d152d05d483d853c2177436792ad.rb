class BeerSong
  BOT = lambda {|n| n.eql?(1) ? '' : 's'}

  def verse(n)
    case n
    when 0
        "No more bottles of beer on the wall, no more bottles of beer.\n" +
        "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    when 1
        "1 bottle of beer on the wall, 1 bottle of beer.\n" +
        "Take it down and pass it around, no more bottles of beer on the wall.\n"
    when 2..99
        "#{n} bottles of beer on the wall, #{n} bottles of beer.\n" +
        "Take one down and pass it around, " +
        "#{n-1} bottle#{BOT.call(n-1)} of beer on the wall.\n"
    end
  end

  def verses(first, last)
    first.downto(last).each_with_object "" do |v, song|
      song << verse(v) << "\n"
    end
  end

  def sing
    verses(99, 0)
  end
end
