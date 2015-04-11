class Complement
  def self.of_dna(x)
    raise ArgumentError, "Non DNA Character" if x.include?('U')
    x.gsub(/[GCTA]/, 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U')
  end
  
  def self.of_rna(x)
    raise ArgumentError, "Non RNA Character" if x.include?('T')
    x.gsub(/[CGAU]/, 'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A')
  end
end
