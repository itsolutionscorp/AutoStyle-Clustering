
class Raindrops

	def self.convert(num)
    	result = ""

		if num.remainder(3) == 0
		  result = "Pling" 
		end
		  
		if num.remainder(5) == 0
		  result += "Plang" 
		end 

		if num.remainder(7) == 0
		  result += "Plong" 
		end

        return result.empty? ? num.to_s  : result 
               
    end
end
