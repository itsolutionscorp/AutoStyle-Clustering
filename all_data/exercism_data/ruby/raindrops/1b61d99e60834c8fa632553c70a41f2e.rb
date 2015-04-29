require 'prime'
class PrimeFactorFinder
  def self.prime_factors(num)
    Prime.prime_division(num).flatten.uniq
  end
end

class Raindrops
  def self.convert(number)
    factors = PrimeFactorFinder.prime_factors(number)
    resp = []
    resp << 'Pling' if factors.include? 3
    resp << 'Plang' if factors.include? 5
    resp << 'Plong' if factors.include? 7
    resp.size > 0 ? resp.join("") : number.to_s
  end
end
