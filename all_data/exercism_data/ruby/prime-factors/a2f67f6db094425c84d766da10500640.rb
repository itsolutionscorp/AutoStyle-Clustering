require 'prime'

class PrimeFactors
  def self.for(number)
    Prime.prime_division(number).map { |p, n| Array.new(n, p) }.flatten
  end
end
