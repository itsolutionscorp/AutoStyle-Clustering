require 'prime'
class Raindrops
  def self.convert(number)
    factors = Prime.prime_division(number)
    factors.flatten!
    raindrop_speak = ""
    raindrop_speak << "Pling" if factors.include?(3)
    raindrop_speak << "Plang" if factors.include?(5)
    raindrop_speak << "Plong" if factors.include?(7)
    raindrop_speak == "" ? number.to_s : raindrop_speak
  end
end
