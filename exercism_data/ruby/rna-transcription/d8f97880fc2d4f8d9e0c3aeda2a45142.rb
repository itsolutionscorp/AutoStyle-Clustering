class Complement
    DNA = "GCTA"
    RNA = "CGAU"
  
  def self.of_dna(dna_strand)
    dna_strand.tr(DNA,RNA)
  end

  def self.of_rna(rna_strand)
    rna_strand.tr(RNA,DNA)
  end
end
