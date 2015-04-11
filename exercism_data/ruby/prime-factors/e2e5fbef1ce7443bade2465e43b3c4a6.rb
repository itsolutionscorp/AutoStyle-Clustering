require 'prime'

class PrimeFactors
  def self.for num
    num.prime_division.reduce([]) do |factors,div|
      factors << ([div.first] * div.last)
    end.flatten
  end
end
