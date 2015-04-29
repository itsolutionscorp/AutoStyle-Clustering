class Complement

  def self.dna_rna_hash
    {   "G"=>"C", 
        "C"=>"G", 
        "T"=>"A", 
        "A"=>"U"
    } 
  end 

  def self.of_dna(dna)
    dna.chars.map {|dna| dna_rna_hash[dna]}.join
  end

  def self.of_rna(dna)
    dna.chars.map {|dna| dna_rna_hash.invert[dna]}.join
  end

end
