class BeerSong
  def verse(bottles)
    case bottles
    when 0; phrase0
    when 1; phrase1
    when 2; phrase2
    else phrasemany(bottles)
    end
  end

  def verses(first, last)
    first.downto(last).map {|i| verse(i)}.join("\n")+"\n"
  end

  def sing
    verses(99,0)
  end

private
  def phrase0; <<PHR
No more bottles of beer on the wall, no more bottles of beer.
Go to the store and buy some more, 99 bottles of beer on the wall.
PHR
  end

  def phrase1; <<PHR
1 bottle of beer on the wall, 1 bottle of beer.
Take it down and pass it around, no more bottles of beer on the wall.
PHR
  end

  def phrase2; <<PHR
2 bottles of beer on the wall, 2 bottles of beer.
Take one down and pass it around, 1 bottle of beer on the wall.
PHR
  end

  def phrasemany(n); <<PHR
#{n} bottles of beer on the wall, #{n} bottles of beer.
Take one down and pass it around, #{n-1} bottles of beer on the wall.
PHR
  end
end
