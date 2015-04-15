class Complement
  DNA_TO_RNA = {'G'=>'C', 'C'=>'G', 'T'=>'A', 'A'=>'U'}
  RNA_TO_DNA = DNA_TO_RNA.invert
  
  def self.of_dna(dna)
    convert(dna, DNA_TO_RNA)
  end
  
  def self.of_rna(rna)
    convert(rna, RNA_TO_DNA)
  end
  
  def self.convert(strand, conversion)
    strand.chars.map{|c| conversion[c] }.join
  end
end
