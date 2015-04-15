require 'prime'

class Raindrops
  def self.convert(num)
    ary = num.prime_division.flat_map {|a, b| [a] * b }
   
    output = ""
    if ary.include?(3) || ary.include?(5) || ary.include?(7)
      output << "Pling" if ary.include? 3
      output << "Plang" if ary.include? 5
      output << "Plong" if ary.include? 7
    else
      output = num.to_s
    end
    
    output
      
  end
end
