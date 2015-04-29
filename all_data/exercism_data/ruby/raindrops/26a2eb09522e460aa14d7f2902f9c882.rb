class Raindrops
	def self.convert(input)
		result = ''
		return input.to_s unless (input % 3 == 0 || input % 5 == 0 || input % 7 == 0)
		if (input % 3 == 0)
			result << 'Pling'
		end
		if (input % 5 == 0)
			result << 'Plang'
		end
		if (input % 7 == 0)
			result << 'Plong'
		end
		return result
	end
end
