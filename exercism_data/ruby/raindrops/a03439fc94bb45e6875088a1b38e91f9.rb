require 'prime'
class Raindrops
  def self.convert(n)
    factors = Prime.prime_division(n).map {|x| x[0]}
    drops = ""
    drops << "Pling" if factors.include?(3)
    drops << "Plang" if factors.include?(5)
    drops << "Plong" if factors.include?(7)
    drops.size == 0 ? n.to_s : drops
  end
end
