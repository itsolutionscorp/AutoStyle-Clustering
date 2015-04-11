class Complement
  
  def self.of_dna(strand)
    strand.split('').map do |nucleotide|
      complements[nucleotide]
    end.join('')
  end
  
  def self.of_rna(strand)
    strand.split('').map do |nucleotide|
      complements.invert[nucleotide]
    end.join('')
  end
  
  def self.complements
    {
      "G" => "C",
      "C" => "G",
      "T" => "A",
      "A" => "U"
    }
  end
  
end
