class Complement
  def self.of_dna(dna)
    if dna_converter[dna[0]]
      dna.chars.map {|char| dna_converter[char] }.join
    else
      raise ArgumentError, "This is an invalid DNA letter"
    end
  end

  def self.dna_converter
    {
      "G" => "C",
      "C" => "G",
      "T" => "A",
      "A" => "U"
    }
  end

  def self.of_rna(rna)
    if dna_converter.invert[rna[0]]
      rna.chars.map {|char| dna_converter.invert[char] }.join
    else
      raise ArgumentError, "This is an invalid RNA letter"
    end
  end

end
