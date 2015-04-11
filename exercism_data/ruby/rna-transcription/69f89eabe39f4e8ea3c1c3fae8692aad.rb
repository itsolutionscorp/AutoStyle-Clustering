class Complement

  DNA_TO_RNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U' 
  }
    
  def self.of_dna(strand)
    strand.chars.map { |nucleotide|  
      DNA_TO_RNA.fetch(nucleotide) { raise ArgumentError } 
    }.join
  end

  def self.of_rna(strand)
    strand.chars.map { |nucleotide|  
      DNA_TO_RNA.invert.fetch(nucleotide) { raise ArgumentError } 
    }.join
  end

end
