class BeerVerse
  MAXIMUM_NUMBER_OF_BOTTLES = 99

  def initialize(verse_number)
    @verse_number = verse_number
  end

  def to_s
    line_one + line_two
  end

  def line_one
    "#{beers_on_the_wall.capitalize}, #{number_of_beers}.\n"
  end

  def line_two
    if @verse_number > 0
      "Take #{one_down} and pass it around, #{next_verse.beers_on_the_wall}.\n"
    else
      "Go to the store and buy some more, #{last_verse.beers_on_the_wall}.\n"
    end
  end

  def beers_on_the_wall
    "#{number_of_beers} on the wall"
  end
  
  def number_of_beers
    "#{beer_count} #{bottle} of beer"
  end
  
  def beer_count
    @verse_number == 0 ? "no more" : @verse_number
  end
  
  def bottle
    @verse_number == 1 ? "bottle" : "bottles"
  end

  def one_down
    @verse_number > 1 ? "one down" : "it down"
  end

  def next_verse
    self.class.new @verse_number - 1
  end

  def last_verse
    self.class.new(MAXIMUM_NUMBER_OF_BOTTLES)
  end
end

class BeerSong
  def verse(verse_number)
    "#{BeerVerse.new(verse_number)}"
  end

  def verses(upper, lower)
    (lower..upper).to_a.reverse.map do |verse_number|
      "#{verse(verse_number)}\n"
    end.join
  end

  def sing
    verses(99, 0)
  end
end
