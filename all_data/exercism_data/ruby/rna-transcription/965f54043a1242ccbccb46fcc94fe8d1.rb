class Complement

  RNA_MAPPING = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(dna_strand)
    dna_strand.chars.map { |c| RNA_MAPPING[c] }.join
  end

  def self.of_rna(rna_strand)
    rna_strand.chars.map { |c| RNA_MAPPING.key(c) }.join
  end

end
