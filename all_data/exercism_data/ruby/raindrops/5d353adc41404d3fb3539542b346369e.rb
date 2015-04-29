require 'prime'

class Raindrops
  def self.convert(num)
    new = []
    ary = num.prime_division
    ary.length.times do |n|
      ary[n][1].times {new << ary[n][0]}
    end

    output = ""

    if new.include?(3) || new.include?(5) || new.include?(7)
      output << "Pling" if new.include? 3
      output << "Plang" if new.include? 5
      output << "Plong" if new.include? 7
    else
      output = num.to_s
    end
    
    output
      
  end
end
