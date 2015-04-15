class Complement
  RNA = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
  DNA = { 'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A' }
  def self.of_dna(dna)
    dna.each_char.inject('') { |a, e| a << RNA[e] }
  end
  def self.of_rna(rna)
    rna.each_char.inject('') { |a, e| a << DNA[e] }
  end
end
