require 'prime'

class PrimeFactors
  def self.for(number)
    Prime.each_with_object([]) do |prime, factors|
      return factors if number == 1

      while number % prime == 0 do
        factors << prime
        number /= prime
      end
    end
  end
end
