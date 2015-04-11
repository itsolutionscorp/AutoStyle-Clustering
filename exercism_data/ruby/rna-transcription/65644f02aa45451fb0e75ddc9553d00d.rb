class Complement
	def self.of_dna(strand)
		if strand == ''
			return ''

		else
			case strand[0]
				when 'G'
					return 'C' + of_dna(strand[1..-1])
				when 'C'
					return 'G' + of_dna(strand[1..-1])
				when 'T'
					return 'A' + of_dna(strand[1..-1])
				when 'A'
					return 'U' + of_dna(strand[1..-1])
				else
					puts "Bad DNA strand."
					exit
				end
		end
	end

	def self.of_rna(strand)
		if strand == ''
			return ''

		else
			case strand[0]
				when 'G'
					return 'C' + of_rna(strand[1..-1])
				when 'C'
					return 'G' + of_rna(strand[1..-1])
				when 'A'
					return 'T' + of_rna(strand[1..-1])
				when 'U'
					return 'A' + of_rna(strand[1..-1])
				else
					puts "Bad RNA strand."
					exit
				end
		end
	end
end
