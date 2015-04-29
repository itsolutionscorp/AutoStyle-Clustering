require 'prime'

class Raindrops

  def self.convert(n)
    factors = Prime.prime_division(n).flatten

    drops = ""

    drops << "Pling" if factors.include? 3
    drops << "Plang" if factors.include? 5
    drops << "Plong" if factors.include? 7

    drops = n.to_s if drops.empty?

    return drops
  end
end
