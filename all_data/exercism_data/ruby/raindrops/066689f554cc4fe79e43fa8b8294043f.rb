class Raindrops
	def self.convert(number)
		result = ""
		result.concat("Pling") if number % 3 == 0 
		result.concat("Plang") if number % 5 == 0 
		result.concat("Plong") if number % 7 == 0
		result == "" ? "#{number}" : result
	end
end  
