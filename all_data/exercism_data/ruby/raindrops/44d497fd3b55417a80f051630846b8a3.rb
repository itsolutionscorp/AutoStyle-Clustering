require "prime"

class Raindrops
  SPEECH = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }

  attr_reader :number

  def self.convert(number)
    new(number).convert
  end

  def initialize(number)
    @number = number
  end

  def convert
    string = prime_words.join
    string.empty? ? number.to_s : string
  end

  def prime_words
    prime_factors.map { |factor| SPEECH[factor] }
  end

  def prime_factors
    Prime.prime_division(number).map { |factor, _| factor }
  end
end
