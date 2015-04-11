require 'prime'
class PrimeFactors
  def self.for(num)
    Prime.prime_division(num).map do |divisors|
      [divisors.first] * divisors.last
    end.flatten
  end
end
