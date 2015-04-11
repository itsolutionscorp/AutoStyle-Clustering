require 'prime'
class Raindrops

  def self.convert(number)
    prime_factors = number.prime_division
    unique_factors = prime_factors.flatten.uniq
    
    if unique_factors.include?(7)
      if unique_factors.include?(5) && unique_factors.include?(3)
        'PlingPlangPlong'
      elsif unique_factors.include?(5)
        "PlangPlong"
      elsif unique_factors.include?(3)
        "PlingPlong"
      else
        "Plong" 
      end
    elsif unique_factors.include?(5)
      if unique_factors.include?(3)
        "PlingPlang"
      else
        "Plang"
      end
    elsif unique_factors.include?(3)
      "Pling"
    else
      number.to_s
    end
  end
end
