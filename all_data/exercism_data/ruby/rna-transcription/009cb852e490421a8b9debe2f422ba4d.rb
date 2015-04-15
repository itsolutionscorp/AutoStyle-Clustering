class Complement
  def self.of_dna(nucleotide)
    nucleotide.gsub(/[ACTG]/, 'A' => 'U', 'C' => 'G', 'T' => 'A', 'G' => 'C')
  end

  def self.of_rna(nucleotide)
    nucleotide.gsub(/[UGAC]/, 'U' => 'A', 'G' => 'C', 'A' => 'T', 'C' => 'G')
  end
end
