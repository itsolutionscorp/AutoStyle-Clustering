require 'forwardable'

class BeerSong
  def verse(number)
    fragments = build_fragments(number)
    Verse.new(fragments).to_s
  end

  def verses(start, ending = 0)
    song = ""

    start.downto(ending) do |number|
      song << verse(number) + "\n"
    end

    song
  end

  private

  def build_fragments(number)
    case number
    when 0
      FragmentsZero.new(number)
    when 1
      FragmentsOne.new(number)
    when 2
      FragmentsTwo.new(number)
    else
      Fragments.new(number)
    end
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
    "#{bottles.capitalize} of beer on the wall, #{bottles} of beer.\n"
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
    bottle_number + bottle_noun
  end

  def remainder_bottles
    next_bottle_number + next_bottle_noun
  end

  def pronoun
    "one"
  end

  def action
    "Take #{pronoun} down and pass it around, "
  end

  def next_number
    number - 1
  end

  private

  def bottle_noun
    "bottles"
  end

  def bottle_number
    "#{number} "
  end

  def next_bottle_number
    "#{next_number} "
  end

  def next_bottle_noun
    "bottles"
  end
end

class FragmentsZero < Fragments
  def action
    "Go to the store and buy some more, "
  end

  def next_number
    99
  end

  def bottle_number
    "no more "
  end
end

class FragmentsOne < Fragments
  def pronoun
    "it"
  end

  def bottle_noun
    "bottle"
  end

  def next_bottle_number
    "no more "
  end
end

class FragmentsTwo < Fragments
  def next_bottle_noun
    "bottle"
  end
end
