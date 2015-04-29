module SentenceCase
  refine String do
    def sentence_case
      self[0].upcase << self[1..length]
    end
  end
end

using SentenceCase

class BeerSong
  def verse(number)
    Verse.say(number)
  end

  def verses(from, to)
    from.downto(to).each_with_object("") do |number, verses|
      verses << verse(number) << "\n"
    end
  end

  def sing
    verses(99, 0)
  end
end

module Verse
  def self.say(number)
    "#{BottlesOfBeer.say(number).sentence_case} on the wall, #{BottlesOfBeer.say(number)}.\n" <<
    "#{action(number)}, #{BottlesOfBeer.say(number - 1)} on the wall.\n"
  end

  def self.action(number)
    return "Go to the store and buy some more" if number.zero?
    pronoun = number == 1 ? "it" : "one"
    "Take #{pronoun} down and pass it around"
  end
end

module BottlesOfBeer
  def self.say(quantity)
    "#{number(quantity)} #{unit(quantity)} of beer"
  end

  def self.number(quantity)
    return 99 if quantity == -1
    return "no more" if quantity.zero?
    quantity
  end

  def self.unit(quantity)
    quantity == 1 ? "bottle" : "bottles"
  end
end
