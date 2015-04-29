class Complement
  @dna_rna_mappings = ['GCTA', 'CGAU']
  def self.of_dna(sequence)
    sequence.tr(*@dna_rna_mappings)
  end

  def self.of_rna(sequence)
    sequence.tr(*@dna_rna_mappings.reverse)
  end
end
