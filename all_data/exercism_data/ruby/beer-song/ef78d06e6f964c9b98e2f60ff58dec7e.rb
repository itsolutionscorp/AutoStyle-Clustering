class BeerSong
  def sing
    verses(99, 0)
  end

  def verses(start, finish)
    start.downto(finish).inject('') { |acc, n| acc + verse(n) + "\n" }
  end

  def verse(n)
    case n
    when 0 then verse_0
    when 1 then verse_1
    when 2 then verse_2
    else chorus(n)
    end
  end

  private

  def chorus(n)
    line_1 = "#{n} bottles of beer on the wall, #{n} bottles of beer.\n"
    line_2 = "Take one down and pass it around, #{n - 1} bottles of beer on the wall.\n"

    line_1 + line_2
  end

  def verse_2
    line_1 = "2 bottles of beer on the wall, 2 bottles of beer.\n"
    line_2 = "Take one down and pass it around, 1 bottle of beer on the wall.\n"

    line_1 + line_2
  end

  def verse_1
    line_1 = "1 bottle of beer on the wall, 1 bottle of beer.\n"
    line_2 = "Take it down and pass it around, no more bottles of beer on the wall.\n"

    line_1 + line_2
  end

  def verse_0
    line_1 = "No more bottles of beer on the wall, no more bottles of beer.\n"
    line_2 = "Go to the store and buy some more, 99 bottles of beer on the wall.\n"

    line_1 + line_2
  end
end
