module Raindrops
  
  def self.convert num
    res = ''
    res << 'Pling' if num % 3 == 0
    res << 'Plang' if num % 5 == 0
    res << 'Plong' if num % 7 == 0
    
    res.empty? ? num.to_s : res
  end
  
end
