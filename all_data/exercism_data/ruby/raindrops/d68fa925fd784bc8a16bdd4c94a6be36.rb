  require 'prime'
class Raindrops

  def self.convert(num)
    prime_factors = num.prime_division
    rain = ""
    prime_factors.each{|prime_factor, exponent|
      if prime_factor == 3 then rain<< "Pling"
      elsif prime_factor == 5 then rain<< "Plang"
      elsif prime_factor == 7 then rain<< "Plong"
      end
    }
    if rain == ""; rain = num end
    return rain.to_s
   
  end
  
   
end
