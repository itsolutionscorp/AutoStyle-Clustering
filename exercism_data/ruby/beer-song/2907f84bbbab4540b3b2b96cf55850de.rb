class BeerSong

  def initialize
  end

  def verse beers
    if beers > 2
      "#{beers} bottles of beer on the wall, #{beers} bottles of beer.\nTake one down and pass it around, #{beers - 1} bottles of beer on the wall.\n"
    elsif beers == 2
      "2 bottles of beer on the wall, 2 bottles of beer.\nTake one down and pass it around, 1 bottle of beer on the wall.\n"
    elsif beers == 1
      "1 bottle of beer on the wall, 1 bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
    else
      "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
    end
  end

  def verses start, finish
    (finish..start).reverse_each.with_object("") do |beers, song|
      song << verse(beers) << "\n"
    end
  end

  def sing
    verses(99,0)
  end

end
