require 'prime'

class Raindrops
  def self.convert(num)
    factors = Prime.prime_division(num).map(&:first)
    plinged = factors.reduce("") do |acc, factor|
      acc + if factor == 3
        "Pling"
      elsif factor == 5
        "Plang"
      elsif factor == 7
        "Plong"
      else
        ""
      end
    end
    plinged.empty? ? num.to_s : plinged
  end
end
