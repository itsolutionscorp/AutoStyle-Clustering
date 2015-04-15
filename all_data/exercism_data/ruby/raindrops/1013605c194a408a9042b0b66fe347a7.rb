require 'prime'

class Raindrops
  def self.convert(number)
    new(number).convert
  end

  attr_reader :factors, :number

  def initialize(number)
    @number  = number
    @factors = Prime.prime_division(number).flatten
  end

  def convert
    return number.to_s unless target_present?

    noises.each_with_object('') do |(target_factor, noise), output|
      output << noise if factors.include?(target_factor)
    end
  end

  private

  def noises
    {
      3 => 'Pling',
      5 => 'Plang',
      7 => 'Plong'
    }
  end

  def target_factors
    noises.keys
  end

  def target_present?
    (factors & target_factors).any?
  end
end
