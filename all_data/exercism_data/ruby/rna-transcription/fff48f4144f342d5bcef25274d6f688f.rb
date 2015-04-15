class Complement
  @hash = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
  def self.of_dna(dna)
    dna.chars.map {|char| @hash[char]}.join
  end
  def self.of_rna(dna)
    dna.chars.map { |char| @hash.invert[char] }.join
  end
end
