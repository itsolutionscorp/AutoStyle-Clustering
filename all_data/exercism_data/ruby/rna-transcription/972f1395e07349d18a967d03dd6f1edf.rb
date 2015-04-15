class Complement

  @rna_to_dna = { "G" => "C",
                  "C" => "G",
                  "T" => "A",
                  "A" => "U" }

  def self.of_dna(dna)
        @rna_to_dna.fetch(dna)
  end

  def self.of_rna(rna)
    @rna_to_dna.invert[rna]
  end

end
