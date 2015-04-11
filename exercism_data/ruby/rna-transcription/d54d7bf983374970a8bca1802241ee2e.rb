class Complement
  ERROR_MESSAGE = "Not a valid RNA or DNA strand."

  DNA_NUCLEOTIDES = "GCTA"
  RNA_NUCLEOTIDES = "CGAU"

  def self.of_dna(dna_strand)
    raise_if_invalid_strand(DNA_NUCLEOTIDES, dna_strand)

    dna_strand.tr(DNA_NUCLEOTIDES, RNA_NUCLEOTIDES)
  end

  def self.of_rna(rna_strand)
    raise_if_invalid_strand(RNA_NUCLEOTIDES, rna_strand)

    rna_strand.tr(RNA_NUCLEOTIDES, DNA_NUCLEOTIDES)
  end

  def self.raise_if_invalid_strand(valid_strand, strand)
    unless strand.scan(/[^#{valid_strand}]/).empty?
      raise ArgumentError, ERROR_MESSAGE
    end
  end

  private_class_method :raise_if_invalid_strand
end
