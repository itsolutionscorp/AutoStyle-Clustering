class Complement
  def self.of_dna(dna)
    table = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
    dna.chars.map { |c| table[c] }.join
  end
  
  def self.of_rna(rna)
    table = { 'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A' }
    rna.chars.map { |c| table[c] }.join
  end
end
