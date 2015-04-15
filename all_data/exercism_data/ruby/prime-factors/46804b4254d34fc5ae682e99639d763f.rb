require 'prime'

class PrimeFactors
  def self.for(num)
    num.prime_division.reduce([]) { |a,(p,e)| a += [p] * e }
  end
end
