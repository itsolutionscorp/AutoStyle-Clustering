class Hamming
  def self.compute(strand_a, strand_b)
    mutations = 0
    shortest_strand_length(strand_a, strand_b).times do |i|
      mutations += 1 if strand_a[i] != strand_b[i]
    end
    mutations
  end

  def self.shortest_strand_length(strand_a, strand_b)
    [strand_a, strand_b].min.length
  end
end
