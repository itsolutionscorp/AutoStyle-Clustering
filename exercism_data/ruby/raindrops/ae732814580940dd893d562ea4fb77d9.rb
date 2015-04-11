
class Raindrops

	def self.convert(num)

    	sounds = ""

		if divisible_by_3?(num)
		  sounds.concat "Pling" 
		end
		  
		if divisible_by_5?(num)
		  sounds.concat "Plang" 
		end 

		if divisible_by_7?(num)
		  sounds.concat "Plong" 
		end

    	return result.empty? ? num.to_s  : result 
               
    end

    def self.divisible_by_3?(num)
  		num.remainder(3) == 0
    end

    def self.divisible_by_5?(num)
    	num.remainder(5) == 0
    end

    def self.divisible_by_7?(num)
    	num.remainder(7) == 0
    end

end
