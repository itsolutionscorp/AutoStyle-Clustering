class Complement

  @nucleotide_hash = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(nucleotides)
  	rna = nucleotides.chars.map do |nucleotide|
      @nucleotide_hash[nucleotide]
  	end
  	rna.join
  end

  def self.of_rna(nucleotides)
  	dna = nucleotides.chars.map do |nucleotide|
      @nucleotide_hash.key(nucleotide)
  	end
  	dna.join
  end
end
