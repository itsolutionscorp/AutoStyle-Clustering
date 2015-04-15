class Complement
  def self.of_dna(dna)
    if dna_to_rna[dna[0]]
      dna.chars.map {|letter| dna_to_rna[letter]}.join
      # dna_to_rna[dna]
    else
      raise ArgumentError, "This is an invalid DNA letter"
    end
  end

  def self.dna_to_rna
    {
      "C" => "G",
      "G" => "C",
      "T" => "A",
      "A" => "U"
    }
  end

  def self.of_rna(rna)
    if dna_to_rna.invert[rna[0]]
      rna.chars.map {|letter| dna_to_rna.invert[letter]}.join
    else
      raise ArgumentError, "This is an invalid RNA letter"
    end
  end
end
