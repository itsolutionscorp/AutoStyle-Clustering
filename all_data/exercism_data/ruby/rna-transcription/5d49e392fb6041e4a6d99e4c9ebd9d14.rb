class Complement

	TO_RNA = {
		'C' => 'G', 
		'G' => 'C',
		'T' => 'A',
		'A' => 'U',
	}

	TO_DNA = {
		'C' => 'G', 
		'G' => 'C',
		'A' => 'T',
		'U' => 'A',
	}

	def self.of_dna(dna)
		dna.gsub(/[GCTA]/, TO_RNA)
	end

	def self.of_rna(rna)
		rna.gsub(/[CGAU]/, TO_DNA)
	end
	
end
