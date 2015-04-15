class BeerSong
  def verse(number)
    Verse.new(number).sing
  end

  def verses(starting_verse, ending_verse=0)
    starting_verse.downto(ending_verse).
      each_with_object("") { |number, song|
      song << verse(number) + "\n"
    }
  end
end

class Verse
  attr_reader :number
  def initialize(number)
    @number = number
  end

  def sing
    "#{bottle.number.capitalize} bottle#{bottle.plural} of beer on the wall, " +
    "#{bottle.number} bottle#{bottle.plural} of beer.\n" +
    bottle.imperative +
    "#{next_bottle.number} bottle#{next_bottle.plural} of beer on the wall.\n"
  end

  def bottle
    @bottle ||= Bottle.new(number)
  end

  def next_bottle
    next_number = (number - 1).modulo(100)
    @next_bottle ||= Bottle.new(next_number)
  end
end

class Bottle
  def initialize(number)
    @number = number
  end

  def number
    @number == 0 ? "no more" : @number.to_s
  end

  def plural
    @number == 1 ? "" : "s"
  end

  def imperative
    if @number == 0
      "Go to the store and buy some more, "
    else
      "Take #{one} down and pass it around, "
    end
  end

  private
  def one
    @number == 1 ? "it" : "one"
  end
end
