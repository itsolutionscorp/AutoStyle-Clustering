class Complement
  def self.of_dna(dna)
    raise ArgumentError if dna=='U'
    dna.gsub(/[CGTA]/, 'C' => 'G', 'G' => 'C',
      'T' => 'A', 'A' => 'U')
  end

  def self.of_rna(rna)
    raise ArgumentError if rna=='T'
    rna.gsub(/[CGUA]/, 'C' => 'G', 'G' => 'C',
      'U' => 'A', 'A' => 'T')
  end

end
