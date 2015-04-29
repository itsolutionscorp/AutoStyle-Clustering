class Complement

  def self.of_dna(dna)
    dna.chars.map { |char| transpositions[char] }.join
  end

  def self.of_rna(rna)
    rna.chars.map { |char| transpositions.invert[char] }.join
  end

  private

  def self.transpositions
    { 'C' => 'G', 'G' => 'C', 'T' => 'A', 'A' => 'U' }
  end
end
