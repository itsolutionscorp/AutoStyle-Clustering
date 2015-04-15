class Complement
  
  def self.of_dna(sequence)
    sequence.chars do |x|
      if x == "U"
        raise ArgumentError, "Invalid DNA necleotide."
      end
    end
    sequence.tr("CGTA", "GCAU")
  end

  def self.of_rna(sequence)
    sequence.chars do |x|
      if x == "T" 
        raise ArgumentError, "Invalid RNA nucleotide." 
      end
    end
    sequence.tr("CGAU", "GCTA")
  end

end
