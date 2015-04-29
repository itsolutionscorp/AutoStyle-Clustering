class Raindrops
	def self.convert(num)
		s = ""
		
		unless num%3 == 0 or num%5 == 0 or num%7 == 0
			return num.to_s
		end
		
		s += "Pling" if num%3 == 0
		s += "Plang" if num%5 == 0			
		s += "Plong" if num%7 == 0
			
		return s

	end
end
