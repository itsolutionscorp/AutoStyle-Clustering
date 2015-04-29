require 'prime'

class PrimeFactors
  def self.for(num)
    Prime.prime_division(num).map { |group| Array.new(group.last, group.first) }.flatten
  end
end
