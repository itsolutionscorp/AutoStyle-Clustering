class Complement
  DNA_RNA = ['GCTA', 'CGAU']
  
  def self.of_dna(complement)
    complement.tr(*DNA_RNA)
  end
  
  def self.of_rna(complement)
    complement.tr(*DNA_RNA.reverse)
  end
end
