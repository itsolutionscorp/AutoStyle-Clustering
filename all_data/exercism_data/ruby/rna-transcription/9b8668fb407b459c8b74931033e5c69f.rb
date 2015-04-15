class Complement

	def self.of_dna(dna)
		dna.chars.map! { |char| Complement::DNA_COMPLEMENTS[char] }.join
	end

	def self.of_rna(rna)
		rna.chars.map! { |char| Complement::RNA_COMPLEMENTS[char] }.join
	end	

	DNA_COMPLEMENTS = {
		'G' => 'C',
		'C' => 'G',
		'T' => 'A',
		'A' => 'U',
	}

	RNA_COMPLEMENTS = {
		'C' => 'G',
		'G' => 'C',
		'A' => 'T',
		'U' => 'A',
	}

end
