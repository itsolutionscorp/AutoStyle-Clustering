require 'prime'

class Raindrops

  FACTOR_REPLACEMENTS = {3 => "Pling", 5 => "Plang", 7 => "Plong" }
    
  def self.convert(number)
    result = ""
    Prime.prime_division(number).map do |factor, exponent|
      result += FACTOR_REPLACEMENTS[factor] if FACTOR_REPLACEMENTS.has_key?(factor)      
    end
    result = number.to_s if result.empty?
    result
  end
end
