require 'pry'

class Raindrops
  
  def self.convert(num)

    drops = {
    	3 => "Pling",
    	5 => "Plang",
    	7 => "Plong"
    }

    drop_str = ""

  	(1..num).each do |i|
      if num % i == 0 && drops.keys.include?(i)
      	drop_str += drops[i]
      end
    end

    if drop_str.empty?
    	num.to_s
    else
    	drop_str
    end

  end

end
