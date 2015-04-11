class Complement
	def self.of_dna(strand)
		strand = strand.split('')
		value = ''
		strand.each do |nucleotide|
			case nucleotide
			when 'G'
				value = value + 'C'
			when 'C'
				value = value + 'G'
			when 'T'
				value = value + 'A'
			when 'A'
				value = value + 'U'
			when 'U'
				value = value + 'A'
			else
				raise 'Wrong input'
			end
		end
		value
	end
	def self.of_rna(strand)
		strand = strand.split('')
		value = ''
		strand.each do |nucleotide|
			case nucleotide
			when 'G'
				value = value + 'C'
			when 'C'
				value = value + 'G'
			when 'A'
				value = value + 'T'
			when 'U'
				value = value + 'A'
			else
				raise 'Wrong input'
			end
		end
		value
	end
end
