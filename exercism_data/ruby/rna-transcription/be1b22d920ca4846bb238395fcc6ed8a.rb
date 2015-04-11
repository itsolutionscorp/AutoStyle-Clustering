class Complement

  DNA = 'GCTA'
  RNA = 'CGAU'

  def self.of_dna nuc
    nuc.tr(DNA, RNA)
  end

  def self.of_rna nuc
    nuc.tr(RNA, DNA)
  end

end
