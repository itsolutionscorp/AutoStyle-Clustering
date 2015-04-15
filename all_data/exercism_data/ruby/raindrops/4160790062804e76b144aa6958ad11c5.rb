class Raindrops
  def self.convert(number)
    rain = ""
    
    rain << "Pling" if number%3 == 0 
    rain << "Plang" if number%5 == 0 
    rain << "Plong" if number%7 == 0
    rain = number.to_s if rain.empty? 
    
    rain
  end
end
