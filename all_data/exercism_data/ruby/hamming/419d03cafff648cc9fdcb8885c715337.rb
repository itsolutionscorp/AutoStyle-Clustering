class Hamming

  def self.compute(dna_a, dna_b)
    [dna_a.size, dna_b.size].min.times.count { |i| dna_a[i] != dna_b[i] }
  end

end
