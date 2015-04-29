module Complement

  def self.of_dna(dna_string)
    if /[^GCTA]/ =~ dna_string
      raise ArgumentError
    end

    dna_string.tr "GCTA", "CGAU"
  end

  def self.of_rna(rna_string)
    if /[^CGAU]/ =~ rna_string
      raise ArgumentError
    end

    rna_string.tr "CGAU", "GCTA"
  end
end
