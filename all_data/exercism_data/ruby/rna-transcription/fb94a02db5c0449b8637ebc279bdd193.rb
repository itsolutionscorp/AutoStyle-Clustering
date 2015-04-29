class Complement
  def self.of_dna(str)
    str.gsub(/[GCTA]/, 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U')
  end

  def self.of_rna(str)
    str.gsub(/[CGAU]/, 'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A')
  end
end
