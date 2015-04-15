class Hamming

  def self.compute(dna_a, dna_b)
    hamming = 0
    [dna_a.size, dna_b.size].min.times do |i|
      hamming += 1 if dna_a[i] != dna_b[i]
    end
    hamming
  end

end
