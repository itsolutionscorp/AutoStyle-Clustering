class Complement

	@dna_complement_of = {
		'C' => 'G',
		'G' => 'C',
		'T' => 'A',
		'A' => 'U'
 	}

  def self.of_dna nucleotides
  	complement = ''
  	nucleotides.each_char { |nucleotide| complement += @dna_complement_of[nucleotide] }
  	complement
  end

	@rna_complement_of = {
		'C' => 'G',
		'G' => 'C',
		'U' => 'A',
		'A' => 'T'
 	}

  def self.of_rna nucleotides
  	complement = ''
  	nucleotides.each_char { |nucleotide| complement += @rna_complement_of[nucleotide] }
  	complement
  end

end
