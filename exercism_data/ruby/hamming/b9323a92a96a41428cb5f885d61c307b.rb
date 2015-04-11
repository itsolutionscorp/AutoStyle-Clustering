class Hamming

	def self.compute(orig, new)

		differences = 0
		
		max_length = determine_max_length(orig, new)
		
		pointer = 0
		while (pointer < max_length)
			if (orig[pointer] != new[pointer])
				differences += 1
			end

			pointer += 1
		end

		differences
	end

	def self.determine_max_length(a, b)
		if a.length > b.length 
			b.length
		else 
			a.length
		end
	end

end
