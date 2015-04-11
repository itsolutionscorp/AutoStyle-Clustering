require 'prime'

class PrimeFactors
  def self.for(number)
    Prime.prime_division(number).map do |factor, exponent|
      Array.new(exponent, factor)
    end.flatten
  end
end
