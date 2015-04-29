class Complement
  VALID_DNA_STRAND = /[^GCTA]/
  VALID_RNA_STRAND = /[^CGAU]/

  def self.of_dna(dna_strand)
    raise_if_invalid_strand VALID_DNA_STRAND, dna_strand

    dna_strand.tr "GCTA", "CGAU"
  end

  def self.of_rna(rna_strand)
    raise_if_invalid_strand VALID_RNA_STRAND, rna_strand

    rna_strand.tr "CGAU", "GCTA"
  end

  private

  def self.raise_if_invalid_strand(validator, strand)
    if validator =~ strand
      raise ArgumentError, "Not a valid RNA or DNA strand."
    end
  end

  private_class_method :raise_if_invalid_strand
end
