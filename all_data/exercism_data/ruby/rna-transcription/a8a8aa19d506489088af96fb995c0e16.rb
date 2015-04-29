class Complement

  @@dna_to_rna_map = { "C"=>"G", "G"=>"C", "T"=>"A", "A"=>"U" }
  @@rna_to_dna_map = { "C"=>"G", "G"=>"C", "U"=>"A", "A"=>"T" }
 
  def self.of_dna strand
    strand.chars.map{ |nuc| self.dna_to_rna(nuc) }.join
  end

  def self.of_rna strand
    strand.chars.map{ |nuc| self.rna_to_dna(nuc) }.join
  end

  def self.dna_to_rna nucleotide
    @@dna_to_rna_map[nucleotide]
  end

  def self.rna_to_dna nucleotide
    @@rna_to_dna_map[nucleotide]
  end

end
