class Complement
  DNA_TO_RNA = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
  RNA_TO_DNA = Hash[ DNA_TO_RNA.map { |k,v| [ v, k ] } ]

  def self.of_dna(s) s.chars.map { |c| DNA_TO_RNA[c] }.join end
  def self.of_rna(s) s.chars.map { |c| RNA_TO_DNA[c] }.join end
end
