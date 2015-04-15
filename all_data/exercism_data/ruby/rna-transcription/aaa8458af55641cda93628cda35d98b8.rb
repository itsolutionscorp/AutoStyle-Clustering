class Complement
	def self.of_dna(original)
		for i in 0..(original.length-1)
			case original[i]
			when 'C'
				original[i] = 'G'
			when 'G'
				original[i] = 'C'
			when 'T'
				original[i] = 'A'
			when 'A'
				original[i] = 'U'
			end
		end
		return original
	end

	def self.of_rna(original)
		for i in 0..(original.length-1)
			case original[i]
			when 'C'
				original[i] = 'G'
			when 'G'
				original[i] = 'C'
			when 'U'
				original[i] = 'A'
			when 'A'
				original[i] = 'T'
			end
		end
		return original
	end
end
