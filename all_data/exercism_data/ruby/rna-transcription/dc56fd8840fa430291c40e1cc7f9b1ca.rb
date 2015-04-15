class Complement
  DNA_COMPLEMENT = ["GCTA", "CGAU"]
  RNA_COMPLEMENT = DNA_COMPLEMENT.reverse

  def self.of_dna(dna)
    dna.tr(*DNA_COMPLEMENT)
  end
  
  def self.of_rna(rna)
    rna.tr(*RNA_COMPLEMENT)
  end
end
