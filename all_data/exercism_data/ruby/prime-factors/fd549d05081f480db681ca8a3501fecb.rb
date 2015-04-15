require 'prime'

module PrimeFactors
  def self.for(n)
    Prime.prime_division(n).map do |(factor, occurences)|
      Array.new(occurences, factor)
    end.flatten
  end
end
