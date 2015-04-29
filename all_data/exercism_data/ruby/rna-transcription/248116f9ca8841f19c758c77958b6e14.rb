class Complement
  
  Code   = 'GCTA'
  Decode = 'CGAU'

  def self.of_dna(stra)
    stra.tr(Code,Decode)
  end
  
  def self.of_rna(stra)
    stra.tr(Decode,Code)
  end
  
end
