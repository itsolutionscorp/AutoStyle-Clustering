class Raindrops
	
	def self.convert(n)
		result = ""
		divisible = false
		
		if n % 3 == 0
			divisible = true
			result << "Pling"
		end
		if n % 5 == 0
			divisible = true
			result << "Plang"
		end
		if n % 7 == 0
			divisible = true
			result << "Plong"
		end

		divisible ? result : n.to_s
	end
	
end
