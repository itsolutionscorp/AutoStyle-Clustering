class Complement
  DNA_REPLACEMENTS = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
  RNA_REPLACEMENTS = DNA_REPLACEMENTS.invert

  def self.of_dna(str)
    str.gsub(/./, DNA_REPLACEMENTS)
  end
  def self.of_rna(str)
    str.gsub(/./, RNA_REPLACEMENTS)
  end
end
