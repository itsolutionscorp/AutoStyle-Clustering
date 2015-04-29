module Complement

  DNA = { "G" => "C", 
          "C" => "G", 
          "T" => "A", 
          "A" => "U" }

  RNA = DNA.invert
  
  def self.of_dna(rna)
    rna.chars.map { |char| DNA[char] }.join
  end

  def self.of_rna(dna)
    dna.chars.map { |char| RNA[char] }.join 
  end
end
