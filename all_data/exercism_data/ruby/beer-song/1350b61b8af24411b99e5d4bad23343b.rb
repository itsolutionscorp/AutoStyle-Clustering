class BeerSong
  def verse(n)
    case n
    when 2
      "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
    when 1
      "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
    when 0
      "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    else
      "#{n} bottles of beer on the wall, #{n} bottles of beer.\nTake one down and pass it around, #{n - 1} bottles of beer on the wall.\n"
    end
  end

  def verses(first, last)
    first.downto(last).each_with_object('') do |v, song|
      song << verse(v) << "\n"
    end
  end

  def sing
    verses(99, 0)
  end
end
