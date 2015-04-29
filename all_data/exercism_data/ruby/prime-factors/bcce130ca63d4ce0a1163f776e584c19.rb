require 'prime'

class PrimeFactors
  def self.for(number)
    number.prime_division
          .map { |prime, cardinality| Array.new cardinality, prime }
          .flatten
  end
end
