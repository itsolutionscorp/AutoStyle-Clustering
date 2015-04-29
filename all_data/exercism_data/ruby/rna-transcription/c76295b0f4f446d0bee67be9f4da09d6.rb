class Complement
  RNA_NUCLEOTIDES = "CGAU"
  DNA_NUCLEOTIDES = "GCTA"
  def Complement.of_dna( strand )
    strand.tr(DNA_NUCLEOTIDES, RNA_NUCLEOTIDES)
  end

  def Complement.of_rna( strand )
    strand.tr(RNA_NUCLEOTIDES, DNA_NUCLEOTIDES )
  end
end
