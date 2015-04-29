class Complement

  @@dna_to_rna_map = { "C"=>"G", "G"=>"C", "T"=>"A", "A"=>"U" }
 
  def self.of_dna strand
    strand.chars.map{ |nuc| @@dna_to_rna_map[nuc] }.join
  end

  def self.of_rna strand
    strand.chars.map{ |nuc| @@dna_to_rna_map.key(nuc) }.join
  end

end
