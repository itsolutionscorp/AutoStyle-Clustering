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
    divisible_by?(number, 3) ? word + "Pling" : word
  end

  def self.plang(number, word)
    divisible_by?(number, 5) ? word + "Plang" : word
  end

  def self.plong(number, word)
    divisible_by?(number, 7) ? word + "Plong" : word
  end

  def self.divisible_by?(number, divisor)
    (number % divisor).zero?
  end

  attr_reader :number
end
