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
    song = ""
    starting_verse.downto(ending_verse).each do |n|
      song += verse(n) + "\n"
    end
    song
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
  attr_reader :number, :plural, :one, :next, :next_plural
  def initialize(number)
    @number = number.to_s
    @plural = "s"
    @one = "one"
    @next = number - 1
    @next_plural = last_verse?(@next) ? "" : "s"
  end

  def sing
    stanza = Stanza.new(self)
    stanza.sing_first + stanza.sing_second
  end

  def last_verse?(number)
    number == 1
  end
end

class PenultimateVerse
  attr_reader :number, :plural, :one, :next, :next_plural
  def initialize
    @number = "1"
    @plural = ""
    @one = "it"
    @next = "no more"
    @next_plural = "s"
  end

  def sing
    stanza = Stanza.new(self)
    stanza.sing_first + stanza.sing_second
  end
end

class FinalVerse
  attr_reader :number, :plural, :one, :next, :next_plural
  def initialize
    @number = "no more"
    @plural = "s"
    @one = "it"
    @next = "99"
    @next_plural = "s"
  end

  def sing
    stanza = Stanza.new(self)
    stanza.sing_first + stanza.sing_final
  end
end

class Stanza

  attr_reader :verse
  def initialize(verse)
    @verse = verse
  end

  def sing_first
    first_line + second_line
  end

  def sing_second
    third_line + fourth_line
  end

  def sing_final
    third_line_final + fourth_line
  end

  private
  def first_line
    "#{verse.number.capitalize} bottle#{verse.plural} of beer on the wall, "
  end

  def second_line
    "#{verse.number} bottle#{verse.plural} of beer.\n"
  end

  def third_line
    "Take #{verse.one} down and pass it around, "
  end

  def third_line_final
    "Go to the store and buy some more, "
  end

  def fourth_line
    "#{verse.next} bottle#{verse.next_plural} of beer on the wall.\n"
  end
end
