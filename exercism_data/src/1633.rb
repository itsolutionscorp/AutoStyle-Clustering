def compute(strand1, strand2)
    mutations = 0
    min_size = [strand1.size, strand2.size].min
    min_size.times do |i|
      mutations += 1 if strand1[i] != strand2[i]
    end
    mutations
  end