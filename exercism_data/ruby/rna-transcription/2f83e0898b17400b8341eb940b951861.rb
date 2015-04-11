class Complement
  DNA_TO_RNA =
    {
      "G" => "C",
      "C" => "G",
      "T" => "A",
      "A" => "U"
    }

  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(dna_strand)
    map_complement(dna_strand, DNA_TO_RNA)
  end

  def self.of_rna(rna_strand)
    map_complement(rna_strand, RNA_TO_DNA)
  end

  private
  def self.map_complement(strand, conversion_map)
    strand.chars.map { |nucleotide| conversion_map[nucleotide]  }.join('')
  end
end
