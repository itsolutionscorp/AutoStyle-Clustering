class Complement

	DNA_COMPLEMENT_OF = {
		'C' => 'G',
		'G' => 'C',
		'T' => 'A',
		'A' => 'U'
 	}

	RNA_COMPLEMENT_OF = DNA_COMPLEMENT_OF.invert

 	class << self
	  def of_dna nucleotides
	  	convert nucleotides, DNA_COMPLEMENT_OF
	  end

	  def of_rna nucleotides
	  	convert nucleotides, RNA_COMPLEMENT_OF
	  end

	  def convert strand, conversion_dictionary
	  	strand.chars.map { |nucleotide| conversion_dictionary[nucleotide] }.join
	  end
	end
end
