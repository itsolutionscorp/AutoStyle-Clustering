class BeerSong
  def verse(number)
    Verse.new(number).to_s
  end

  def verses(start, ending = 0)
    song = ""

    start.downto(ending) do |number|
      song << verse(number) + "\n"
    end

    song
  end
end

class Verse
  attr_reader :number

  def initialize(number)
    @number = number
  end

  def to_s
    start_stanza + middle_stanza + final_stanza
  end

  private

  def start_stanza
    "#{bottles.capitalize} of beer on the wall, #{bottles} of beer.\n"
  end

  def middle_stanza
    if number == 0
      "Go to the store and buy some more, "
    else
      "Take #{pluralizer} down and pass it around, "
    end
  end

  def final_stanza
    "#{remainder_bottles} of beer on the wall.\n"
  end

  def pluralizer
    number == 1 ? "it" : "one"
  end

  def remainder_bottles
    Bottle.new(next_number).to_s
  end

  def bottles
    Bottle.new(number).to_s
  end

  def next_number
    number == 0 ? 99 : number - 1
  end
end

class Bottle
  attr_reader :number

  def initialize(number)
    @number = number
  end

  def to_s
    case number
    when 0
      "no more bottles"
    when 1
      "#{number} bottle"
    else
      "#{number} bottles"
    end
  end
end
