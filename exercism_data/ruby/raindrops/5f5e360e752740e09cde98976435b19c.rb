require 'prime'

class Raindrops
  DROPS = {Pling: 3, Plang: 5, Plong: 7}
  
  def self.convert i
    @i = i
    raindrops = DROPS.map {|key, val| @i%val==0 ? key.to_s : ""}.join
    raindrops.empty? ? i.to_s : raindrops
  end
end
  
