class Beer
  def sing(start, finish=0)
    start.downto(finish).each_with_object("") do |number_of_beers, result|
      result << verse(number_of_beers)
      result << "\n"
    end
  end

  def verse(number_of_beers)
    result = BeerSongPhrases.start(number_of_beers)
    result << BeerSongPhrases.action_for(number_of_beers)
    result << BeerSongPhrases.ending(number_of_beers)
    result
  end
end

class BeerSongPhrases
  def self.start(number_of_beers)
    "#{count(number_of_beers)} of beer on the wall, #{count(number_of_beers)} of beer.\n".capitalize
  end

  def self.action_for(number_of_beers)
    if number_of_beers == 0
      "Go to the store and buy some more,"
    else
      "Take #{refer_to_beer(number_of_beers)} down and pass it around,"
    end.capitalize
  end

  def self.ending(number_of_beers)
    " #{count(number_of_beers - 1)} of beer on the wall.\n"
  end

  def self.count(number_of_beers)
    number_of_beers %= 100
    case number_of_beers
    when 0 then "no more bottles"
    when 1 then "1 bottle"
    else "#{number_of_beers} bottles"
    end
  end

  def self.refer_to_beer(number_of_beers)
    case number_of_beers
    when 1 then "it"
    else "one"
    end
  end
end
