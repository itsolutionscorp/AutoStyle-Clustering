module Complement

  def self.of_dna(dna_string)
    valid_strand? /[^GCTA]/, dna_string

    dna_string.tr "GCTA", "CGAU"
  end

  def self.of_rna(rna_string)
    valid_strand? /[^CGAU]/, rna_string

    rna_string.tr "CGAU", "GCTA"
  end

  private

  def self.valid_strand?(regex, strand)
    if regex =~ strand
      raise ArgumentError
    end
  end
end
