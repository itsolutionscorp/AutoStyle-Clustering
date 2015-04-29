require 'prime'

class PrimeFactors
  def self.for(n)
    Prime.prime_division(n).flat_map { |x| Array.new(x[1], x[0]) }
  end
end
