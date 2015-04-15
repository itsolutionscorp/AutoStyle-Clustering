require 'forwardable'

class BeerSong
  def verse(number)
    fragments = Fragments.new(number)

    Verse.new({
      number: number,
      fragments: fragments
      }).to_s
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
  extend Forwardable

  def_delegators :fragments,
                 :bottles,
                 :remainder_bottles,
                 :pronoun,
                 :next_number

  attr_reader :number, :fragments

  def initialize(args)
    @number    = args[:number]
    @fragments = args[:fragments]
  end

  def to_s
    start_stanza + middle_stanza + final_stanza
  end

  private

  def start_stanza
    "#{bottles.capitalize} of beer on the wall, #{bottles} of beer.\n"
  end

  def middle_stanza
    fragments.action
  end

  def final_stanza
    "#{remainder_bottles} of beer on the wall.\n"
  end
end

class Fragments
  attr_reader :number

  def initialize(number)
    @number = number
  end

  def bottles
    bottle_number(number) + bottle_noun(number)
  end

  def remainder_bottles
    bottle_number(next_number) + bottle_noun(next_number)
  end

  def pronoun
    number == 1 ? "it" : "one"
  end

  def action
    if number == 0
      "Go to the store and buy some more, "
    else
      "Take #{pronoun} down and pass it around, "
    end
  end

  def next_number
    number == 0 ? 99 : number - 1
  end

  private

  def bottle_noun(number)
    number == 1 ? "bottle" : "bottles"
  end

  def bottle_number(number)
    number == 0 ? "no more " : "#{number} "
  end
end
