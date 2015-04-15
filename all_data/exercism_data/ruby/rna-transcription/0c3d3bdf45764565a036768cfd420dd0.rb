class Complement

  DNA = 'CATG'
  RNA = 'GUAC'#amole

  def self.of_dna(nucleotides)
    nucleotides.tr(DNA, RNA)
  end

  def self.of_rna(nucleotides)
    nucleotides.tr(RNA, DNA)
  end

end
