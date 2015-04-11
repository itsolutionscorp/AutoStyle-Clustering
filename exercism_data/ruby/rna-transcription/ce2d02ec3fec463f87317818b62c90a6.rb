class Complement
  @@rna = "CGAU"
  @@dna = "GCTA"
  def Complement.of_dna( dna )
    dna.tr(@@dna, @@rna)
  end

  def Complement.of_rna( rna )
    rna.tr(@@rna, @@dna)
  end
end
