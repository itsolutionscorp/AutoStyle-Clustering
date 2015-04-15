require 'prime'

class Raindrops

  def self.convert(num)
    factors = Prime.prime_division(num)
    raindrops = ""
    factors.each do |p, v|
      if p == 3
        raindrops += 'Pling'
      elsif p == 5
        raindrops += 'Plang'
      elsif p == 7
        raindrops += "Plong"
      end
    end
    raindrops = num.to_s if raindrops.empty?
    return raindrops
  end
  
end
