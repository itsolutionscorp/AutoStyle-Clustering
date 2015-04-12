def compute(strand_a,strands_b)
      return -1 if strand_a.length != strands_b.length
      distance = 0
      (strand_a.length).times do |i|
        distance += 1 if strand_a[i] != strands_b[i]
      end
      distance
    end