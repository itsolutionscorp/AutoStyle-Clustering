class Complement
  def self.replacement_matrix
    {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}
  end
  
  def self.rna_complement(nucleotide)
    replacement_matrix[nucleotide]
  end
  
  def self.of_dna(strand)
    strand.chars.map{|nucleotide| rna_complement(nucleotide)}.join("")
  end
end
