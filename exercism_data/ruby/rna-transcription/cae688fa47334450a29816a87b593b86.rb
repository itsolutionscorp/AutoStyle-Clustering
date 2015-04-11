class Complement
  
  def self.of_dna dna_sequence
    rna_sequence = dna_sequence.tr("GCTA", "CGAU")
    return rna_sequence
  end

  def self.of_rna rna_sequence
    dna_sequence = rna_sequence.tr("CGAU", "GCTA")
    return dna_sequence
  end

end
