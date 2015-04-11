require 'prime'
require 'pry'

class Raindrops
  def self.convert(int)
    prime_factors = prime_factors_for(int)
  
    # 3,5,7 / Pling,Plang,Plong
    result  = prime_factors.include?(3) ? "Pling" : ""
    result += prime_factors.include?(5) ? "Plang" : ""
    result += prime_factors.include?(7) ? "Plong" : ""

    result.length > 0 ? result : int.to_s
  end

  private

  def self.prime_factors_for(i)
    i.prime_division.flatten
  end
end
