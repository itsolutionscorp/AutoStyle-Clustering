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

  def self.number_if_empty(number, string)
    string.empty? ? number.to_s : string
  end

  def self.pling(number, word)
    sound(number, word, 3, "Pling")
  end

  def self.plang(number, word)
    sound(number, word, 5, "Plang")
  end

  def self.plong(number, word)
    sound(number, word, 7, "Plong")
  end

  def self.sound(number, word, factor, sound)
    divisible_by?(number, factor) ? word + sound : word
  end

  def self.divisible_by?(number, divisor)
    (number % divisor).zero?
  end

  attr_reader :number
end
