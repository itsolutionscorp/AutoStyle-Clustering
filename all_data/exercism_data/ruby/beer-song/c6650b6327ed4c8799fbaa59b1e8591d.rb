class BeerSong
  def verse(n)
    main_verse(n) << next_verse(n)
  end

  def verses(first, last)
    first.downto(last).map(&method(:verse)).join("\n") << "\n"
  end

  def sing
    verses 99, 0
  end

  def main_verse(n)
    if n  == 1
      "#{n} bottle of beer on the wall, #{n} bottle of beer.\n"
    elsif n == 0
      "No more bottles of beer on the wall, no more bottles of beer.\n"
    else
      "#{n} bottles of beer on the wall, #{n} bottles of beer.\n"
    end
  end

  def next_verse(n)
    if n == 2
      "Take one down and pass it around, 1 bottle of beer on the wall.\n"
    elsif n == 1
      "Take it down and pass it around, no more bottles of beer on the wall.\n"
    elsif n == 0
      "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    else
      "Take one down and pass it around, #{n-1} bottles of beer on the wall.\n"
    end
  end
end
