require 'prime'

class Raindrops
  
  def self.convert(num)
    
    factors = num.prime_division.map{|e|e.first}    
    
    s = ""
    if factors.include? 3
      s << "Pling"
    end
    if factors.include? 5
      s << "Plang"
    end
    if factors.include? 7
      s << "Plong"
    end
    
    if s.empty?
      s << num.to_s
    end

    return s
  end
  
  
  
end
