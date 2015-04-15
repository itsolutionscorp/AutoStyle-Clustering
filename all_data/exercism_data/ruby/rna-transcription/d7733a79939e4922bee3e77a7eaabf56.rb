class Complement

  TRANS = {dna:'CGTA', rna:'GCAU'}

  def self.of_dna(dna_seq)
    dna_seq.tr(TRANS[:dna], TRANS[:rna])
  end
  
  def self.of_rna(rna_seq)
    rna_seq.tr(TRANS[:rna], TRANS[:dna])
  end
  
end
