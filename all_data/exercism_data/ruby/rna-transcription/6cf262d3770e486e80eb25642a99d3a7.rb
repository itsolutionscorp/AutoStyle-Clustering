module Complement

  RNA = 'GCUA'
  DNA = 'CGAT'
  
  def self.of_dna(str)
    str.tr(DNA, RNA)
  end

  def self.of_rna(str)
    str.tr(RNA, DNA)
  end

end
