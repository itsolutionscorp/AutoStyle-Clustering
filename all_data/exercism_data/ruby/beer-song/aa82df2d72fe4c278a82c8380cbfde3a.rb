class Beer

  def verse(beers)
    if beers > 2
      many_beers_left(beers)
    elsif beers == 2
      two_beers_left(beers)
    elsif beers == 1
      one_beer_left(beers)
    else
      no_beers_left(beers)
    end
  end

  def sing(starting_line, ending_line = 0)
    lines = (ending_line .. starting_line).to_a.reverse

    lines.inject("") do |song, line|
      song << verse(line)
      song << "\n"
    end

  end


  def many_beers_left(beers)
    "#{beers} bottles of beer on the wall, #{beers} bottles of beer.\nTake one down and pass it around, #{beers - 1} bottles of beer on the wall.\n"
  end

  def two_beers_left(beers)
"#{beers} bottles of beer on the wall, #{beers} bottles of beer.\nTake one down and pass it around, #{beers - 1} bottle of beer on the wall.\n"
  end

  def one_beer_left(beers)
"#{beers} bottle of beer on the wall, #{beers} bottle of beer.\nTake it down and pass it around, no more bottles of beer on the wall.\n"
  end

  def no_beers_left(beers)
    "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
  end

end
