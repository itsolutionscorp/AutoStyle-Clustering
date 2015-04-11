class Beer

  def sing(first_beer,last_beer=0)
    first_beer.downto(last_beer).
      map { |beers| verse(beers) }.join(inhale) + inhale
  end

  def verse(beers) 
    if beers.zero?
      count_beers_on_the_wall(beers).capitalize + 
        go_to_the_store_and_buy_some_more
    else
      count_beers_on_the_wall(beers) + 
        take_one_down(beers)
    end
  end

  private

  def inhale
    "\n"
  end

  def count_the(beers)
    return "no more bottles of beer" if beers == 0
    return "1 bottle of beer" if beers == 1
    "#{beers} bottles of beer"
  end

  def count_beers_on_the_wall(beers)
    "#{count_the(beers)} on the wall, #{count_the(beers)}.\n"
  end

  def go_to_the_store_and_buy_some_more
    "Go to the store and buy some more, 99 bottles of beer on the wall.\n"
  end

  def take_one_down(beers)
    if beers > 1
      "Take one down and pass it around, #{count_the(beers - 1)} on the wall.\n"
    else
      "Take it down and pass it around, no more bottles of beer on the wall.\n"
    end
  end
end
