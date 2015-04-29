class BeerSong
  def verse(number)
    if last_verse?(number)
      FinalVerse.new
    elsif penultimate_verse?(number)
      PenultimateVerse.new
    else
      Verse.new(number)
    end.sing
  end

  def verses(starting_verse, ending_verse=0)
    starting_verse.downto(ending_verse).
      each_with_object("") { |number, song|
      song << verse(number) + "\n"
    }
  end

  private
  def penultimate_verse?(number)
    number == 1
  end

  def last_verse?(number)
    number == 0
  end
end

class Verse
  attr_reader :number, :imperitive
  def initialize(number)
    @number = number
  end

  def sing
    "#{number.to_s.capitalize} bottle#{plural} of beer on the wall, " +
    "#{number} bottle#{plural} of beer.\n" +
    imperitive +
    "#{next_number} bottle#{next_plural} of beer on the wall.\n"
  end

  def plural
    "s"
  end

  def one
    "one"
  end

  def next_number
    number - 1
  end

  def next_plural
    penultimate_verse? ? "" : "s"
  end

  def imperitive
    "Take #{one} down and pass it around, "
  end

  def penultimate_verse?
    next_number == 1
  end
end

class PenultimateVerse < Verse
  def initialize
    @number = 1
  end

  def plural
    ""
  end

  def one
    "it"
  end

  def next_number
    "no more"
  end

  def next_plural
    "s"
  end
end

class FinalVerse < Verse
  def initialize
    @number = "no more"
  end

  def imperitive
    "Go to the store and buy some more, "
  end

  def next_number
    "99"
  end
end
