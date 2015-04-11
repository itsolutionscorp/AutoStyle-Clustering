require 'Prime'

class PrimeFactors

  def self.for(number)
    factors = []
    return factors if number == 1

    while !Prime.prime?(number) do
      factors << Prime.find { |prime| number % prime == 0 }
      number /= factors[-1]
    end
    factors << number
  end

end
