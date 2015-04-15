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
    rna_strand = map_complement(dna_strand, dna_to_rna)
  end

  def self.of_rna(rna_strand)
    dna_strand = map_complement(rna_strand, rna_to_dna)
  end

  def self.map_complement(strand, conversion_map)
    complement_strand = ''
    strand.each_char do |nucleotide|
      complement_strand << conversion_map[nucleotide]
    end
    complement_strand
  end
end
