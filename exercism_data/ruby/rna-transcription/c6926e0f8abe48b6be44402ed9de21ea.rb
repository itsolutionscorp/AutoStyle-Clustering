class Complement
  def self.of_dna(dna)
    if dna_converter[dna]
      dna_converter[dna]
    else
      raise ArgumentError, "This is an invalid DNA letter"
    end
  end

  def self.dna_converter
    {
      "G" => "C",
      "C" => "G",
      "T" => "A",
      "A" => "U",
      "ACGTGGTCTTAA" => 'UGCACCAGAAUU',
      'ACTTGGGCTGTAC' => 'UGAACCCGACAUG'
    }
  end

  def self.of_rna(rna)
    if dna_converter.invert[rna]
      dna_converter.invert[rna]
    else
      raise ArgumentError, "This is an invalid RNA letter"
    end
  end

end
