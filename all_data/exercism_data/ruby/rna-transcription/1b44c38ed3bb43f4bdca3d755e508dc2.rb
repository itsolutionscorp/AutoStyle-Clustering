class Complement

  def self.of_dna(dna_string)
    fail ArgumentError unless dna_string =~ /[GCTA]/
    dna_string.gsub(/[GCTA]/, 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U')
  end

  def self.of_rna(rna_string)
    fail ArgumentError unless rna_string =~ /[CGAU]/
    rna_string.gsub(/[CGAU]/, 'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A')
  end

end
