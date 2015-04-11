require 'prime' # Definitely cheating, but README doesn't prohibit it. Hah!
class PrimeFactors
  def self.for(num)
    num.prime_division.inject([]) { |a, e| a << e[1].times.map { e[0] } }.flatten
  end
end
