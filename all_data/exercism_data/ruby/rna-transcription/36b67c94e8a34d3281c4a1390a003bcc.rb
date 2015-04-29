class Complement
  @nucleotide_lookup = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }

  def self.of_dna(sequence)
    rna_complement = ''

    sequence.chars.each do |nucleotide|
      rna_complement << @nucleotide_lookup[nucleotide].to_s
    end
    rna_complement
  end

  def self.of_rna(sequence)
    dna_complement = ''

    sequence.chars.each do |nucleotide|
      dna_complement << @nucleotide_lookup.key(nucleotide).to_s
    end
    dna_complement
  end
end
