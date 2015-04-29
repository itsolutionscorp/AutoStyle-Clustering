class Complement

  DNA2RNA = ['GCTA','CGAU']

  def self.of_dna(dna)
    dna.tr(*DNA2RNA)
  end

  def self.of_rna(rna)
    rna.tr(*DNA2RNA.reverse)
  end

end
