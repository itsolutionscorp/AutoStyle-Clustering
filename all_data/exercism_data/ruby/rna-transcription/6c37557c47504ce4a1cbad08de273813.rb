class Complement
  
  COMPLEMENTS = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }
  
  def self.of_dna(dna)
    raise ArgumentError unless dna.is_a? String
    dna.chars.map{|c| COMPLEMENTS[c]}.join
  end

  def self.of_rna(rna)
    raise ArgumentError unless rna.is_a? String
    rna.chars.map{|c| COMPLEMENTS.invert[c]}.join
  end

end
