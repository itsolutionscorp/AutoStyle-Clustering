class Complement
  NUCLEOTIDES = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }

  def self.of_dna(sequence)
    rna_complement = ''

    sequence.chars.each do |nucleotide|
      rna_complement << NUCLEOTIDES[nucleotide]
    end
    rna_complement
  end

  def self.of_rna(sequence)
    dna_complement = ''

    sequence.chars.each do |nucleotide|
      dna_complement << NUCLEOTIDES.key(nucleotide)
    end
    dna_complement
  end

end
