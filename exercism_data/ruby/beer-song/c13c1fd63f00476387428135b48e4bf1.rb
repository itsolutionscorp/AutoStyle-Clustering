class Beer
  def sing(start, finish=0)
    start.downto(finish).each_with_object("") do |number_of_beers, result|
      result << verse(number_of_beers)
      result << "\n"
    end
  end

  def verse(number_of_beers)
    result = first_line(number_of_beers).capitalize
    result << second_line(number_of_beers).capitalize
    result
  end

  def first_line(number_of_beers)
    "#{bottles(number_of_beers)} of beer on the wall, #{bottles(number_of_beers)} of beer.\n"
  end

  def second_line(number_of_beers)
    first_sentence = if number_of_beers == 0
      "Go to the store and buy some more,"
    else
      "Take #{the_beer(number_of_beers)} down and pass it around,"
    end
    first_sentence + " #{beers_left(number_of_beers)} of beer on the wall.\n"
  end

  private

  def bottles(number_of_beers)
    case number_of_beers
    when 0 then "no more bottles"
    when 1 then "1 bottle"
    else "#{number_of_beers} bottles"
    end
  end

  def the_beer(number_of_beers)
    case number_of_beers
    when 1 then "it"
    else "one"
    end
  end

  def beers_left(number_of_beers)
    number_of_beers_left = (number_of_beers - 1) % 100
    case number_of_beers_left
    when 0 then "no more bottles"
    when 1 then "1 bottle"
    else "#{number_of_beers_left} bottles"
    end
  end
end
