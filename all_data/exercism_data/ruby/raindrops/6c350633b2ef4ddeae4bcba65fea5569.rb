class Raindrops
  def self.convert(drop)
    drip = drop
    rainsound = {}
    self.splitdroplet(drip,rainsound)
    if (rainsound.empty?)
      return drop.to_s
    end
    rainsound.keys.join
  end

  def self.splitdroplet(drip,rainsound)
    
    nomorefactors =false
    if drip % 2 == 0 
      drip = drip / 2
    elsif drip % 3 == 0 
      drip = drip / 3
      rainsound['Pling'] = 0
    elsif drip % 5 == 0
      drip = drip / 5
      rainsound['Plang'] = 0
    elsif drip % 7 == 0
      drip = drip / 7 
      rainsound['Plong'] = 0
    else
      nomorefactors = true
    end
    
    self.splitdroplet(drip,rainsound) unless nomorefactors
    rainsound
  end

end


Raindrops.convert(10)
