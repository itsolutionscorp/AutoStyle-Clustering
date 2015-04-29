class Raindrops

	def self.convert(string)
		result = ""
		# string.to_i
		result << "Pling" if string % 3 == 0
		result << "Plang" if string % 5 == 0
		result << "Plong" if string % 7 == 0 
		result = string.to_s if result.empty?
		result
	end
end
