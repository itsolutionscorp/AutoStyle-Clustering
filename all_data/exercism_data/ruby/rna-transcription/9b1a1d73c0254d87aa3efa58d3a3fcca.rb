class Complement

	def self.of_dna(dna_sequence)
		transcribed_rna_sequence = ""
		dna_sequence.length.times do |i|
			case dna_sequence[i]
			when 'A'
				transcribed_rna_sequence += "U"
			when 'T'
				transcribed_rna_sequence += "A"
			when 'C'
				transcribed_rna_sequence += "G"
			when 'G'
				transcribed_rna_sequence += "C"
			end
		end
		transcribed_rna_sequence
	end

	def self.of_rna(rna_sequence)
	transcribed_dna_sequence = ""
	rna_sequence.length.times do |i|
		case rna_sequence[i]
		when 'U'
			transcribed_dna_sequence += "A"
		when 'A'
			transcribed_dna_sequence += "T"
		when 'C'
			transcribed_dna_sequence += "G"
		when 'G'
			transcribed_dna_sequence += "C"
		end
	end
	transcribed_dna_sequence
	end

end
