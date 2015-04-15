class Complement

  def self.of_dna(strand)
    strand.gsub(/[ACGT]/,
    'A' => 'U', 'C' => 'G', 'T' => 'A', 'G' => 'C')
  end

  def self.of_rna(strand)
    strand.gsub(/[UGAC]/,
    'U' => 'A', 'G' => 'C', 'A' => 'T', 'C' => 'G')
  end
end
