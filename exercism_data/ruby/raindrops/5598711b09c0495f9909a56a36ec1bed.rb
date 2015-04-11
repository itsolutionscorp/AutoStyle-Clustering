class Raindrops
	def self.convert(input)
		result = ''
		if (input % 3 == 0)
			result << 'Pling'
		end
		if (input % 5 == 0)
			result << 'Plang'
		end
		if (input % 7 == 0)
			result << 'Plong'
		end
		result == '' ? (return input.to_s) : (return result)
	end
end
