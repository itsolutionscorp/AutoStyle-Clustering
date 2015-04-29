class Complement
  COMPLEMENTS = { "G" => "C",
                  "C" => "G",
                  "T" => "A",
                  "A" => "U"
                }
                
  def self.of_dna(dna)
    dna.chars.map do |let|
      if COMPLEMENTS.has_key?(let)
        COMPLEMENTS[let]
      else
        raise ArgumentError.new("not DNA")
      end
    end.join
  end
  
  def self.of_rna(rna)
    rna.chars.map do |let|
      if COMPLEMENTS.has_value?(let)
        COMPLEMENTS.key(let)
      else
        raise ArgumentError.new("not RNA")
      end
    end.join 
  end                
end
