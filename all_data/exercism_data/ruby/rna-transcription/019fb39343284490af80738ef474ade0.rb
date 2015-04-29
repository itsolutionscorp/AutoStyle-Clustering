module Complement

  def self.of_dna(dna_string)
    if dna_string.include? "U"
      raise ArgumentError
    end

    dna_string.tr "GCTA", "CGAU"
  end

  def self.of_rna(rna_string)
    if rna_string.include? "T"
      raise ArgumentError
    end

    rna_string.tr "CGAU", "GCTA"
  end
end
