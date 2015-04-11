require 'prime'

class Raindrops
  def convert number
    @factors = number.prime_division
    return number.to_s unless factors_contain?(3) || factors_contain?(5) || factors_contain?(7)
    drops = ""
    drops << 'Pling' if factors_contain? 3
    drops << 'Plang' if factors_contain? 5
    drops << 'Plong' if factors_contain? 7
    drops
  end

  def factors_contain? number
    @factors.any? { |factor| factor[0] == number }
  end
end
