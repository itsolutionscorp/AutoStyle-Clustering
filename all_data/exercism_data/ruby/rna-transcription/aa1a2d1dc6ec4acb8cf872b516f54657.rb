class Complement
  def self.dna_rna_map
    {
      "G" => "C",
      "C" => "G",
      "T" => "A",
      "A" => "U"
    }
  end

  def self.of_dna(dna_strand)
    rna_strand = ''
    dna_strand.each_char do |nucleotide|
      rna_strand << dna_rna_map[nucleotide]
    end
    rna_strand
  end

  def self.of_rna(rna_strand)
    dna_strand = ''
    rna_strand.each_char do |nucleotide|
      dna_strand << dna_rna_map.invert[nucleotide]
    end
    dna_strand
  end
end
