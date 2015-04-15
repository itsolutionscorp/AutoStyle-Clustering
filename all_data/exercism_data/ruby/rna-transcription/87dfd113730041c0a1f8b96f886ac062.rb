class Complement

  @dna_to_rna = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(nucleotides)
  	rna = nucleotides.chars.map do |nucleotide|
      @dna_to_rna[nucleotide]
  	end
  	rna.join
  end

  def self.of_rna(nucleotides)
  	dna = nucleotides.chars.map do |nucleotide|
      @dna_to_rna.key(nucleotide)
  	end
  	dna.join
  end
end
