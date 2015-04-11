class Raindrops
  def convert(count)
    factors = PrimeFactors.for(count)

    out = ""
    out << "Pling" if factors.include?(3)
    out << "Plang" if factors.include?(5)
    out << "Plong" if factors.include?(7)

    out.empty? ? count.to_s : out
  end
end


require "prime"
class PrimeFactors
  def self.for(number)
    number.prime_division.flatten.sort.uniq - [1]
  end
end
