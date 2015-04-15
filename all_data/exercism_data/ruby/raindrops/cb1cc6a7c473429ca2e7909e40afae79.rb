class Raindrops
  DROPS = {Pling: 3, Plang: 5, Plong: 7}
  
  def self.convert i
    DROPS.map {|key, val| i%val==0 ? key.to_s : ""}.join.tap{|x| return x.empty? ? i.to_s : x}
  end
end
