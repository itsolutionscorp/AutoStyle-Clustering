class BeerSong
  def verse(number)
    select_verse(number).sing
  end

  def verses(starting_verse, ending_verse=0)
    starting_verse.downto(ending_verse).
      each_with_object("") { |number, song|
      song << verse(number) + "\n"
    }
  end

  private
  def select_verse(number)
    if ultimate_verse?(number)
      UltimateVerse
    elsif penultimate_verse?(number)
      PenultimateVerse
    elsif antepenultimate_verse?(number)
      AntepenultimateVerse
    else
      Verse
    end.new(number)
  end

  def antepenultimate_verse?(number)
    number == 2
  end

  def penultimate_verse?(number)
    number == 1
  end

  def ultimate_verse?(number)
    number == 0
  end
end

class Verse
  attr_reader :number, :imperative
  def initialize(number)
    @number = number
  end

  def sing
    VerseTemplate.new(self).render
  end

  # Allows ERB to execute verse methods
  def get_binding
    binding
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
    "s"
  end

  def imperative
    "Take #{one} down and pass it around, "
  end
end

class AntepenultimateVerse < Verse
  def next_plural
    ""
  end
end

class PenultimateVerse < Verse
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

class UltimateVerse < Verse
  def number
    "no more"
  end

  def imperative
    "Go to the store and buy some more, "
  end

  def next_number
    "99"
  end
end

require 'erb'

class VerseTemplate
  attr_reader :verse
  def initialize(verse)
    @verse = verse
  end

  def render
    ERB.new(string).result(verse.get_binding)
  end

  def string
    "<%= number.to_s.capitalize %> bottle<%= plural %> of beer on the wall, " +
    "<%= number %> bottle<%= plural %> of beer.\n" +
    "<%= imperative %>" +
    "<%= next_number %> bottle<%= next_plural %> of beer on the wall.\n"
  end
end
