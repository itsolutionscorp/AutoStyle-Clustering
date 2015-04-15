require 'Prime'
class Raindrops
  def self.convert(number)
    factors = Prime.prime_division(number).flatten
    if factors.include?(3) && factors.include?(5) && factors.include?(7)
      "PlingPlangPlong"
    elsif factors.include?(3) && factors.include?(5)
      "PlingPlang"
    elsif factors.include?(5) && factors.include?(7)
      "PlangPlong"
    elsif factors.include?(3) && factors.include?(7)
      "PlingPlong"
      elsif factors.include?(3)
        "Pling"
      elsif factors.include?(5)
        "Plang"
      elsif factors.include?(7)
        "Plong"
      else
        number.to_s
    end
  end
end
