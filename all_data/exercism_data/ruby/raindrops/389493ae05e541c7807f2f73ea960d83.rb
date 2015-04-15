require 'prime'

class Raindrops
    
  DROPS = {3 => "Pling", 5 => "Plang", 7 => "Plong"}  
    
  def self.convert(num)
      s = DROPS.map {|n,s| s if num % n == 0 }.join
      return (s.empty?)? num.to_s : s
  end
end
