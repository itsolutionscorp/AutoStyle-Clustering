class Complement
	DNA_TO_RNA = {
		'G' => 'C',
		'C' => 'G',
		'T' => 'A',
		'A' => 'U'
	}

	def self.of_dna(rna)
		convert(rna, DNA_TO_RNA)
	end

	def self.of_rna(dna)
		convert(dna, DNA_TO_RNA.invert)
	end

	def self.convert(strand, conversion_key)
		strand.split('').map {|letter| conversion_key[letter]}.join
	end

end
