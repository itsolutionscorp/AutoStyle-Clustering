class Complement

  @@rna_nucleotides="GCAU"
  @@dna_nucleotides="CGTA"

  def self.of_dna(dna)
    rna=dna.tr(@@dna_nucleotides,@@rna_nucleotides)
  end

  def self.of_rna(rna)
    dna=rna.tr(@@rna_nucleotides,@@dna_nucleotides)
  end

end
