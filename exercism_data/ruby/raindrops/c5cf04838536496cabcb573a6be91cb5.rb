require 'prime'

class Raindrops
  def self.convert(type)
    output = ""
    prime_factors = Prime.prime_division(type).flatten.uniq
    if prime_factors.include?(3)
      output += "Pling"
    end
    if prime_factors.include?(5)
      output += "Plang"
    end
    if prime_factors.include?(7)
      output += "Plong"
    end
    output += "#{type}" if output.empty?
    output
  end
end
