class Complement
  
  def self.of_dna(str)
    str.tr('GCTA', 'CGAU')
  end
  
  def self.of_rna(str)
    str.tr('CGAU', 'GCTA')
  end
  
end
