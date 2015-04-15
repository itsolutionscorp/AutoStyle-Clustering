class Complement
  RNA = 'CGAU'
  DNA = 'GCTA'

  def self.of_dna string
    string.tr DNA, RNA
  end

  def self.of_rna string
    string.tr RNA, DNA
  end
end
