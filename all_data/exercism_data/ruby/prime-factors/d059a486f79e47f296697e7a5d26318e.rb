require 'prime'
module PrimeFactors

  def self.for(number)
    # prime_division returns an array of pairs of   factor, # of occurrences for each prime factor of the number
    Prime.prime_division(number).each_with_object([]) { |factor_set, factors| factor_set[1].times { factors << factor_set[0] } }
  end

end
