class Complement
  @DNA_TO_RNA = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
  def self.of_dna(dna)
    dna.chars.map { |char| @DNA_TO_RNA[char]}.join
  end
  def self.of_rna(dna)
    dna.chars.map { |char| @DNA_TO_RNA.invert[char] }.join
  end
end
