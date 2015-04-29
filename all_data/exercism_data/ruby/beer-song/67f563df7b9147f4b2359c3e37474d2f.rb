class BeerSong
  def verse(bottles)
    phrase[ndx(bottles)] % counts(bottles)
  end

  def verses(first, last)
    first.downto(last).reduce("") {|m,i| m<<verse(i)<<"\n"}
  end

  def sing
    verses(99,0)
  end

  def ndx(bottles)
    bottles > 3 ? 3 : bottles
  end

  def counts(bottles)
    [ ["No more", "no more", 99],
      [bottles, bottles, "no more"],
      [bottles, bottles, bottles-1],
      [bottles, bottles, bottles-1] ][ndx(bottles)]
  end

  def phrase
    [
%Q(%s bottles of beer on the wall, %s bottles of beer.
Go to the store and buy some more, %i bottles of beer on the wall.\n),

%Q(%i bottle of beer on the wall, %i bottle of beer.
Take it down and pass it around, %s bottles of beer on the wall.\n),

%Q(%i bottles of beer on the wall, %i bottles of beer.
Take one down and pass it around, %i bottle of beer on the wall.\n),

%Q(%i bottles of beer on the wall, %i bottles of beer.
Take one down and pass it around, %i bottles of beer on the wall.\n)
    ]
  end
end
