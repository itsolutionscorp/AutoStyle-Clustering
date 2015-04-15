require 'prime'

class Raindrops
  def self.convert(number)
    rain=[]
    if number % 3 == 0 then rain.push("Pling") end
    if number % 5 == 0 then rain.push("Plang") end
    if number % 7 == 0 then rain.push("Plong") end
    
    if rain.length==0 then return number.to_s 
      else return rain.join 
    end    
    end
  end

