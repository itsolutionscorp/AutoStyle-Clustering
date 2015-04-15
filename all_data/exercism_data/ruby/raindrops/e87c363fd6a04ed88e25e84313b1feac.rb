class Raindrops

	CONVERSIONS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}
	
	def self.convert(n)
		result = ""
		CONVERSIONS.each do |(i, word)|
			result << word if n % i == 0
		end

		result.empty? ? n.to_s : result
	end

end
