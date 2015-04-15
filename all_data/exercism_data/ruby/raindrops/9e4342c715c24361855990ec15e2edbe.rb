require 'prime'

class Raindrops

  def self.convert(number)
    new(number).convert
  end

  attr_reader :number

  def initialize(number)
    @number = number
  end

  def convert
    str = ""
    str << "Pling" if pling?
    str << "Plang" if plang?
    str << "Plong" if plong?

    str.empty? ? number.to_s : str
  end

  def factors
    @factors ||= number.prime_division.flatten
  end

  def pling?
    factors.include?(3)
  end

  def plang?
    factors.include?(5)
  end

  def plong?
    factors.include?(7)
  end

end
