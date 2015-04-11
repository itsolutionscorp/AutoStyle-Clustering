class Complement

  TO_RNA = {"C"=>"G", "G"=>"C", "T"=>"A", "A"=>"U"}
  TO_DNA = TO_RNA.invert

  def self.of_dna(strand)    
    strand.chars.map{|c|TO_RNA[c]}.join
  end

  def self.of_rna(strand)    
    strand.chars.map{|c|TO_DNA[c]}.join    
  end
end
