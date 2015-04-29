require 'forwardable'

class BeerSong
  def verse(number)
    fragments = Fragments.new(number)
    Verse.new(fragments).to_s
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
                 :action,
                 :pronoun,
                 :next_number

  attr_reader :fragments

  def initialize(fragment)
    @fragments = fragment
  end

  def to_s
    start_stanza + action + final_stanza
  end

  private

  def start_stanza
    "#{bottles.capitalize} on the wall, #{bottles}.\n"
  end

  def final_stanza
    "#{remainder_bottles} on the wall.\n"
  end
end

class Fragments
  attr_accessor :number

  def initialize(number)
    @number = number
  end

  def bottles
    bottle_number + bottle_noun
  end

  def remainder_bottles
    self.number = next_number
    bottle_number + bottle_noun
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

  def bottle_noun
    number == 1 ? "bottle of beer" : "bottles of beer"
  end

  def bottle_number
    number == 0 ? "no more " : "#{number} "
  end
end
