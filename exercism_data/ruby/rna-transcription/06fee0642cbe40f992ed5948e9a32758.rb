class Complement
  Comp = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
  def self.of_dna(dna_string)
    return dna_string.chars.map { |char| Comp[char] or raise ArgumentError }.join
  end
  def self.of_rna(rna_string)
    return rna_string.chars.map { |char| Comp.invert[char] or raise ArgumentError }.join
  end
end
