require 'prime'

class PrimeFactors
  def self.for(n)
    n.prime_division.each_with_object([]) do |(factor, exponent), factors|
      exponent.times { factors << factor }
    end
  end
end
