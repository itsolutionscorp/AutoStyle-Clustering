class Complement
  RNA = 'GCTA'
  DNA = 'CGAU'

  def self.of_dna(dna)
    dna.tr(RNA, DNA)
  end

  def self.of_rna(rna)
    rna.tr(DNA, RNA)
  end
end
