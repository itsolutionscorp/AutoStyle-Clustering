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
      "Take #{pronoun} down and pass it around, "
    end
  end

  def final_stanza
    "#{remainder_bottles} of beer on the wall.\n"
  end

  def pronoun
    Fragments.new(number).pronoun
  end

  def remainder_bottles
    Fragments.new(next_number).bottles
  end

  def bottles
    Fragments.new(number).bottles
  end

  def next_number
    number == 0 ? 99 : number - 1
  end
end

class Fragments
  attr_reader :number

  def initialize(number)
    @number = number
  end

  def bottles
    bottle_number + bottle_noun
  end

  def pronoun
    number == 1 ? "it" : "one"
  end

  private

  def bottle_noun
    number == 1 ? "bottle" : "bottles"
  end

  def bottle_number
    number == 0 ? "no more " : "#{number} "
  end
end
