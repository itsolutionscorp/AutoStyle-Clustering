require 'prime'

class PrimeFactors
  def self.for number
    Prime.prime_division(number).map { |prime, count| [prime]*count }.flatten
  end
end
