class Complement
  def self.dna_to_rna
    {
      "G" => "C",
      "C" => "G",
      "T" => "A",
      "A" => "U"
    }
  end

  def self.rna_to_dna
    dna_to_rna.invert
  end

  def self.of_dna(dna_strand)
    map_complement(dna_strand, dna_to_rna)
  end

  def self.of_rna(rna_strand)
    map_complement(rna_strand, rna_to_dna)
  end

  def self.map_complement(strand, conversion_map)
    strand.chars.map { |nucleotide| conversion_map[nucleotide]  }.join('')
  end
end
