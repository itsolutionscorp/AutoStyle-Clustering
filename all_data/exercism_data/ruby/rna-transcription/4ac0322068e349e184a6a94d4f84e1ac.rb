class Complement
  COMPLEMENTS = {dna:'CGTA', rna:'GCAU'}

  def self.of_dna(strand)
    strand.tr(COMPLEMENTS[:dna],COMPLEMENTS[:rna])
  end


  def self.of_rna(strand)
    strand.tr(COMPLEMENTS[:rna],COMPLEMENTS[:dna])
  end
end
