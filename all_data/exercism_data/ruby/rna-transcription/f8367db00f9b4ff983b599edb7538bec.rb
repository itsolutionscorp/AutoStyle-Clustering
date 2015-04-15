class Complement
  DNA_RNA = {
    'G' => 'C', 
    'C' => 'G', 
    'T' => 'A', 
    'A' => 'U'
  }
  def self.of_dna(occurence)
    occurence.gsub(/[GCTA]/,DNA_RNA) 
  end

  def self.of_rna(occurence)
    occurence.gsub(/[CGAU]/, DNA_RNA.invert)
  end
end
