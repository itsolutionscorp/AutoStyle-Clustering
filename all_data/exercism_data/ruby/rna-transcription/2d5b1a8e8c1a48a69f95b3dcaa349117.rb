class Complement
  
  def self.of_dna(sequence)

    if sequence.chars.include?("U")
      raise ArgumentError, "Invalid DNA nucleotide."
    end
    sequence.tr("CGTA", "GCAU")
  end

  def self.of_rna(sequence)
    if sequence.chars.include?("T")
      raise ArgumentError, "Invalid RNA nucleotide." 
    end
    sequence.tr("CGAU", "GCTA")
  end

end
