class BeerSong

  def verse(n)
    case n
    when 0 then last_verse
    else 
      n_bottles_of_beer_on_the_wall(n) + ", " + n_bottles_of_beer(n) + take_one_down_and_pass_it_around(n) +n_bottles_of_beer_on_the_wall(n-1) + ".\n"
    end
  end

  def bottle(n)
    case n
    when 0 then "no more bottles"
    when 1 then "#{n} bottle"
    else "#{n} bottles"
    end
  end

  def it_or_one(n)
    n == 1 ? "it" : "one"
  end

  def verses(upper, lower)
    upper.downto(lower).map { |n| "#{verse(n)}\n" }.join
  end

  def sing 
    verses(TOTAL_NUMBER_OF_BOTTLES, 0)
  end

  def last_verse
    "No more bottles of beer on the wall, no more bottles of beer.\nGo to the store and buy some more, 99 bottles of beer on the wall.\n"
  end

  def n_bottles_of_beer_on_the_wall(n)
    "#{bottle(n)} of beer on the wall"
  end 

  def n_bottles_of_beer(n)
    "#{bottle(n)} of beer.\n"
  end

  def take_one_down_and_pass_it_around(n)
    "Take #{it_or_one(n)} down and pass it around, "
  end

  TOTAL_NUMBER_OF_BOTTLES = 99

end
