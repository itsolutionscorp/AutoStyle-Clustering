require 'prime'

class Raindrops
  def self.convert(number)
    rain=[]
    if number % 3 == 0 then rain <<("Pling") end
    if number % 5 == 0 then rain << ("Plang") end
    if number % 7 == 0 then rain << ("Plong") end
 
    if rain.empty? then return number.to_s 
      else return rain.join 
    end    
    end
  end

