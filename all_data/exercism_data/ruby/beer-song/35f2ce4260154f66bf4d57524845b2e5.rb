class BeerSong
  def verse n
    on_the_wall(n) + "\n" + take_down(n) + "\n"
  end

  def verses m, n
    (n..m).map { |i| verse i }.reverse.join("\n") + "\n"
  end

  def sing
    verses 99, 0
  end

  private

  def on_the_wall n
    bottle_of_beers(n).capitalize + " on the wall, " + bottle_of_beers(n) + "."
  end

  def take_down n
    case n
    when 0 then "Go to the store and buy some more"
    when 1 then "Take it down and pass it around"
    else "Take one down and pass it around"
    end + ", " +
    less_bottles(n)
  end

  def bottle_of_beers n
    case n
    when 0 then "no more bottles"
    when 1 then "1 bottle"
    else "#{n} bottles"
    end + " of beer"
  end

  def less_bottles n
    bottle_of_beers(n.pred % 100) + " on the wall."
  end

end
