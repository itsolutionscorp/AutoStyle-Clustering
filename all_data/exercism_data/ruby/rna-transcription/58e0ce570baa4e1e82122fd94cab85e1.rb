class Complement
	REPLACEMENT_MAP = {
		'G' => 'C',
		'C' => 'G',
		'T' => 'A',
		'A' => 'U',
	}

	def self.of_dna(dna_strand)
		rna_strand = ''
		dna_strand.each_char do |character|
			rna_strand = rna_strand + REPLACEMENT_MAP[character]
		end

		rna_strand
	end

	def self.of_rna(rna_strand)
		dna_strand = ''
		rna_strand.each_char do |character|
			dna_strand = dna_strand + REPLACEMENT_MAP.key(character)
		end

		dna_strand
	end
end
