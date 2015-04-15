class Complement
  
  def self.of_dna(sequence)
    raise ArgumentError, "Invalid DNA nucleotide." if sequence.chars.include?("U")
    sequence.tr("CGTA", "GCAU")
  end

  def self.of_rna(sequence)
    raise ArgumentError, "Invalid RNA nucleotide." if sequence.chars.include?("T") 
    sequence.tr("CGAU", "GCTA")
  end

end
