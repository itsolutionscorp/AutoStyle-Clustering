class Complement

  DNA_STRAND_LETTERS = "GCTA"
  RNA_STRAND_LETTERS = "CGAU"

  def self.of_dna(dna_strand)
    raise_if_invalid_strand DNA_STRAND_LETTERS, dna_strand

    dna_strand.tr DNA_STRAND_LETTERS, RNA_STRAND_LETTERS
  end

  def self.of_rna(rna_strand)
    raise_if_invalid_strand RNA_STRAND_LETTERS, rna_strand

    rna_strand.tr RNA_STRAND_LETTERS, DNA_STRAND_LETTERS
  end

  def self.raise_if_invalid_strand(valid_strand, strand)
    unless /[#{valid_strand}]/ =~ strand
      raise ArgumentError, "Not a valid RNA or DNA strand."
    end
  end

  private_class_method :raise_if_invalid_strand
end
