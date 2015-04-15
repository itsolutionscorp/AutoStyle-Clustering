class DNA < Struct.new(:strand)

  DNA_NUCLEOTIDES = "ACGT"
  RNA_NUCLEOTIDES = "ACGU"

  def to_rna
    strand.tr(DNA_NUCLEOTIDES, RNA_NUCLEOTIDES)
  end
end
