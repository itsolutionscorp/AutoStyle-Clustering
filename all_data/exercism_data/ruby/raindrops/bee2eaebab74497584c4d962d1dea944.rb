require 'prime'

class Raindrops
  def self.convert(num)
    factors_array = Prime.prime_division(num).flatten
    if factors_array.include?(3) && factors_array.include?(7) && factors_array.include?(5)
      "PlingPlangPlong"
    elsif factors_array.include?(3) && factors_array.include?(7)
      "PlingPlong"
    elsif factors_array.include?(3) && factors_array.include?(5)
      "PlingPlang"
    elsif factors_array.include?(5) && factors_array.include?(7)
      "PlangPlong"
    elsif factors_array.include?(3)
      "Pling"
    elsif factors_array.include?(7)
      "Plong"
    elsif factors_array.include?(5)
      "Plang"
    else
      num.to_s
    end
  end
end
