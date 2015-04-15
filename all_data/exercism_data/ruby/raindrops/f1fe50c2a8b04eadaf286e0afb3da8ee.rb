class Raindrops

	SUBSTITUTIONS = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

	def self.convert(number)
		str = ""
		SUBSTITUTIONS.each do |k, v| 
			str += v if (number % k) == 0
		end
		
		str.empty? ? number.to_s : str
	end
end
