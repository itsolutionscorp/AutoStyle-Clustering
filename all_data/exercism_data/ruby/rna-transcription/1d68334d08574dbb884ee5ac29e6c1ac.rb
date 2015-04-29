class Complement
  DNA = 'CATG'
  RNA = 'GUAC'

  def self.of_dna(dna_string)
    dna_string.tr(DNA, RNA) 
  end

  def self.of_rna(rna_string)
    rna_string.tr(RNA, DNA) 
  end

end
