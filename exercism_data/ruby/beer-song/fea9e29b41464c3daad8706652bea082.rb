class BeerSong
  def verse(n)
    case n
    when 2
      verse = "#{n} bottles of beer on the wall, #{n} bottles of beer.\n" \
      "Take one down and pass it around, #{n - 1} bottle of beer on the wall.\n"
    when 1
      verse = "#{n} bottle of beer on the wall, #{n} bottle of beer.\n" \
      "Take it down and pass it around, no more bottles of beer on the wall.\n"
    when 0
      verse = "No more bottles of beer on the wall, no more bottles of beer.\n" \
      "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    else
      verse = "#{n} bottles of beer on the wall, #{n} bottles of beer.\n" \
      "Take one down and pass it around, #{n - 1} bottles of beer on the wall.\n"
    end
  end

  def verses(start, finish)
    start.downto(finish).map { |n| verse(n) }.join("\n") + "\n"
  end

  def sing
    self.verses(99, 0)
  end
end
