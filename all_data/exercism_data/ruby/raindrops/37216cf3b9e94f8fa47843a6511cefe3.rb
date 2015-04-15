require "prime"

class Raindrops
  def self.convert n
    factors = Prime.instance.prime_division(n).map(&:first)

    r = []
    r << "Pling" if factors.include? 3
    r << "Plang" if factors.include? 5
    r << "Plong" if factors.include? 7
    r << n if r.empty?

    r.join
  end
end
