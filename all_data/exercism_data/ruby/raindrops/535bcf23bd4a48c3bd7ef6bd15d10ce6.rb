require 'prime'

class Raindrops
  def self.convert(num)
    result = raindrop_speak(num.prime_division.flatten.sort)
    result == "" ? num.to_s : result
  end
  
  def self.raindrop_speak(prime_factor)
    result = ""
    result.tap do 
      { 3 => "Pling", 5 => "Plang", 7 => "Plong" }.each do |k,v|
        result << v if prime_factor.include?(k.to_i)
      end
    end
  end
end
