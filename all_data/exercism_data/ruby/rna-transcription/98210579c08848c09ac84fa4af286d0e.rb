class Complement
	def self.of_dna(dna)
		result = String.new

		for i in 0...dna.length
			case dna[i]
			when 'C'
				result << 'G'
			when 'G'
				result << 'C'
			when 'T'
				result << 'A'
			when 'A'
				result << 'U'
			end
		end

		return result
	end

	def self.of_rna(rna)
		result = String.new

		for i in 0...rna.length
			case rna[i]
			when 'C'
				result << 'G'
			when 'G'
				result << 'C'
			when 'U'
				result << 'A'
			when 'A'
				result << 'T'
			end
		end

		return result
	end
end
