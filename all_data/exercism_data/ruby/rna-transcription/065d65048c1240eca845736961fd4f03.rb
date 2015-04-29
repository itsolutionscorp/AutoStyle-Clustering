class Complement

  def self.of_dna(dna)
    trans = {
      "G" => "C",
      "C" => "G",
      "T" => "A",
      "A" => "U"
    }

    dna.chars.map { |char| trans[char] || raise(ArgumentError) }.join
  end

  def self.of_rna(rna)
    trans = {
      "C" => "G",
      "G" => "C",
      "A" => "T",
      "U" => "A"
    }
    
    rna.chars.map { |char| trans[char] || raise(ArgumentError) }.join
  end

end
