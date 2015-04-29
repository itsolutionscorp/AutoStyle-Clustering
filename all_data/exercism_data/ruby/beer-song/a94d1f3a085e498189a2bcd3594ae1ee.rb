class BeerSong

  def sing
    verses(99,0)
  end

  def verse n
    if n == 0
      "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    elsif n == 1
      "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
    elsif n == 2
      "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
    else
       "#{n} bottles of beer on the wall, #{n} bottles of beer.\nTake one down and pass it around, #{n-1} bottles of beer on the wall.\n"
    end
  end

  def verses(start, finish)
    start.downto(finish).to_a.map {|n| verse(n)}.join("\n") + "\n"
  end

end
