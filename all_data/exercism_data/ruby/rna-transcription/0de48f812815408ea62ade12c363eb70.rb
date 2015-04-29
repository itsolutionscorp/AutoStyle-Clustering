class Complement
  DNA_NUCLEOTIDES = "CGAT"
  RNA_NUCLEOTIDES = "GCUA"

  def self.of_dna(nucleotides)
    transcribe(nucleotides, DNA_NUCLEOTIDES, RNA_NUCLEOTIDES)
  end

  def self.of_rna(nucleotides)
    transcribe(nucleotides, RNA_NUCLEOTIDES, DNA_NUCLEOTIDES)
  end

  def self.transcribe(nucleotides, from, to)
    nucleotides.tr(from, to)
  end
end
