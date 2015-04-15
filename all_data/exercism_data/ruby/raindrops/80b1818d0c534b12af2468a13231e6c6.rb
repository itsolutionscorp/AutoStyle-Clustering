require 'prime'
require 'set'

# pure functions
class Raindrops
  def self.convert(number)
    number_if_empty(number,
      plong(number,
        plang(number,
          pling(number, ""))))
  end

  private

  SOUNDS = {
    pling: 3,
    plang: 5,
    plong: 7
  }

  def self.number_if_empty(number, string)
    string.empty? ? number.to_s : string
  end

  def self.method_missing(symbol, number, word)
    sound(number, word, SOUNDS[symbol], symbol.to_s.capitalize)
  end

  def self.sound(number, word, factor, sound)
    divisible_by?(number, factor) ? word + sound : word
  end

  def self.divisible_by?(number, divisor)
    (number % divisor).zero?
  end
end
