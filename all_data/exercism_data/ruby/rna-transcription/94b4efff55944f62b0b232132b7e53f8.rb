class Complement
  DNA_REPLACEMENTS = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
  RNA_REPLACEMENTS = DNA_REPLACEMENTS.invert
  DNA_NUCLEOTIDES = DNA_REPLACEMENTS.keys
  RNA_NUCLEOTIDES = RNA_REPLACEMENTS.keys

  def self.of_dna(str)
    str.gsub(/[#{DNA_NUCLEOTIDES}]/, DNA_REPLACEMENTS)
  end
  def self.of_rna(str)
    str.gsub(/[#{RNA_NUCLEOTIDES}]/, RNA_REPLACEMENTS)
  end
end
