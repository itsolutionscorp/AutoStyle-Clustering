require "prime"

class Raindrops
  def self.convert n
    factors = n.prime_division.map(&:first)

    r = []
    r << "Pling" if factors.include? 3
    r << "Plang" if factors.include? 5
    r << "Plong" if factors.include? 7
    r << n if r.empty?

    r.join
  end
end
