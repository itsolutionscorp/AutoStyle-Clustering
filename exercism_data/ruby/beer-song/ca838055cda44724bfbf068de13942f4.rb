class BeerSong
  def verse(bottles)
    case bottles
    when 0; Phrase0
    when 1; Phrase1
    when 2; Phrase2
    else    Phrasemany % [bottles, bottles, bottles-1]
    end
  end

  def verses(first, last)
    first.downto(last).reduce("") {|m,i| m<<verse(i)<<"\n"}
  end

  def sing
    verses(99,0)
  end

Phrase0 = <<PHR
No more bottles of beer on the wall, no more bottles of beer.
Go to the store and buy some more, 99 bottles of beer on the wall.
PHR

Phrase1 = <<PHR
1 bottle of beer on the wall, 1 bottle of beer.
Take it down and pass it around, no more bottles of beer on the wall.
PHR

Phrase2 = <<PHR
2 bottles of beer on the wall, 2 bottles of beer.
Take one down and pass it around, 1 bottle of beer on the wall.
PHR

Phrasemany = <<PHR
%i bottles of beer on the wall, %i bottles of beer.
Take one down and pass it around, %i bottles of beer on the wall.
PHR
end
