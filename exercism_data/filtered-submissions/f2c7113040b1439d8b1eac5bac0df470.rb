def compute(a, b)
      min_strand_length = [a.length, b.length].min
      min_strand_length.times.count { |i| a[i] != b[i]}
    end