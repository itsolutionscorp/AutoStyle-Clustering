class Complement
  DNA_COMPLEMENTS = {
    "G"=>"C",
    "C"=>"G",
    "T"=>"A",
    "A"=>"U",
  }
  RNA_COMPLEMENTS = DNA_COMPLEMENTS.invert
  
  def self.of_dna strand
    transform strand, DNA_COMPLEMENTS
  end
  
  def self.of_rna strand
    transform strand, RNA_COMPLEMENTS
  end
  
  def self.transform string, complements
    string.gsub /./, complements
  end
end
