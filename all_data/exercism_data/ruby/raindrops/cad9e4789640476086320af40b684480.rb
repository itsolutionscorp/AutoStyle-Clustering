
class Raindrops

	def convert(num)
    	result = ""

		if num.modulo(3) == 0
		  result = "Pling" 
		end
		  
		if num.modulo(5) == 0
		  result += "Plang" 
		end 

		if num.modulo(7) == 0
		  result += "Plong" 
		end

        result = num.to_s  if result.empty? 
        result        
    end
end
