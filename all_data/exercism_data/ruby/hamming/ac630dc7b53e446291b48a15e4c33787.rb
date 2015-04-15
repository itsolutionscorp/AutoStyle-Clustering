class Hamming
  def self.compute(strand_a, strand_b)
    i = 0
    mutations = 0
    while i < [strand_a, strand_b].min.length
      mutations += 1 if strand_a[i] != strand_b[i]
      i += 1
    end
    mutations
  end
end
