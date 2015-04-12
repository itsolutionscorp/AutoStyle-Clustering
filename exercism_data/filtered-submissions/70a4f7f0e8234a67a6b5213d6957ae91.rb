def compute(strand_a, strand_b)
    min_strand_length = [strand_a.length, strand_b.length].min
    mutations = 0
    min_strand_length.times do |i|
      mutations += 1 if strand_a[i] != strand_b[i]
    end
    mutations
  end