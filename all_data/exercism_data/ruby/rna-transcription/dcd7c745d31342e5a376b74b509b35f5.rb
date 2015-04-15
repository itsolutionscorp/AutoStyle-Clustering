class Complement

  @dna_to_rna = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  @rna_to_dna = @dna_to_rna.invert

  def self.of_dna(dna_nucleotides)
  	rna = dna_nucleotides.chars.map do |nucleotide|
      @dna_to_rna[nucleotide]
  	end
  	rna.join
  end

  def self.of_rna(rna_nucleotides)
  	dna = rna_nucleotides.chars.map do |nucleotide|
      @rna_to_dna[nucleotide]
  	end
  	dna.join
  end
end
