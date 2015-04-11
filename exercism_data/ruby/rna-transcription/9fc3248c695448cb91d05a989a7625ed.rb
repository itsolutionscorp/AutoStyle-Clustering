class Complement
  DNA_RNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  
  def self.of_dna(str)
    process(str, DNA_RNA)
  end
  
  def self.of_rna(str)
    process(str, DNA_RNA.invert)
  end
  
  def self.process(str, hash)
    str.chars.map{|char| hash[char]}.join
  end
  
end
