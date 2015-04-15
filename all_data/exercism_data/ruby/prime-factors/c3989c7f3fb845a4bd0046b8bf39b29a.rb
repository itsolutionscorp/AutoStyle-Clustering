require 'prime'

module PrimeFactors
  def self.for(n)
    Prime.prime_division(n).map { |a| Array.new(a[1], a[0]) }.flatten
  end
end
