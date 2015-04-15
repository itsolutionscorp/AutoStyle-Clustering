module Raindrops
  FACTORS = [[3,'Pling'],[5,'Plang'],[7,'Plong']]
  def self.convert num
    phrase = ''
    FACTORS.each do |factor, noise|
      phrase << noise if num % factor == 0
    end
    
    phrase.empty? ? num.to_s : phrase
  end
  
end
