require 'prime'

class Raindrops
  attr_accessor :num

  def self.convert(num)
    number = new(num)
    number.speak
  end

  def initialize(number)
    @num = number.to_i
  end

  def speak
    raindrop_speak.any? ? raindrop_speak.join : num.to_s
  end

  private

  def prime_factors
    num.prime_division.flatten
  end

  def raindrop_speak
    out = []
    out << "Pling" if prime_factors.include?(3)
    out << "Plang" if prime_factors.include?(5)
    out << "Plong" if prime_factors.include?(7)
    out
  end

end
