class Complement
  def self.of_dna(strand)
    strand.gsub!(/[CGTA]/, 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U')
  end

  def self.of_rna(strand)
    strand.gsub!(/[CGUA]/, 'C' => 'G', 'G' => 'C', 'U' => 'A', 'A' => 'T')
  end

end
