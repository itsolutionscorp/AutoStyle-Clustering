require 'pry'

class Raindrops

  DROPS = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }
  
  def self.convert(num)
    drop_str = ""
  	DROPS.keys.each do |i|
      drop_str += DROPS[i] if num % i == 0
    end
    drop_str.empty? ? num.to_s : drop_str
  end

end
