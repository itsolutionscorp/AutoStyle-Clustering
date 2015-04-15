class Complement
  def self.of_dna(dna)
    rna=dna.tr('CGTA','GCAU')
  end

  def self.of_rna(rna)
    dna=rna.tr('CGUA','GCAT')
  end



end
