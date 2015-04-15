class Complement
  def self.of_dna(complement)
    complement.tr('GCTA', 'CGAU')
  end
  
  def self.of_rna(complement)
    complement.tr('CGAU', 'GCTA')
  end
end
