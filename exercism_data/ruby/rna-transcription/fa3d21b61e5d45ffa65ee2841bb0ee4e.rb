class Complement
  def self.of_dna(strand)
    raise ArgumentError, "Uracil is not a DNA base" if strand.include?('U')
    strand.gsub(/[GCTA]/, 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U')
  end

  def self.of_rna(strand)
    raise ArgumentError, "Thymine is not an RNA base" if strand.include?('T')
    strand.gsub(/[CGUA]/, 'C' => 'G', 'G' => 'C', 'U' => 'A', 'A' => 'T')
  end
end
