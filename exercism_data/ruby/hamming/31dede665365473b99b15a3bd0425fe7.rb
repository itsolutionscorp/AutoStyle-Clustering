class Hamming
  def self.compute(dna_a, dna_b)
    set_a = dna_a.chars
    set_b = dna_b.chars
    set_a.zip(set_b).count {|set| set[0] != set[1]}
  end
end
