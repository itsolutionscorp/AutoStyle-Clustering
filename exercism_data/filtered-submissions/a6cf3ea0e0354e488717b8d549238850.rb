def compute(str_a, str_b)
    min = [str_a, str_b].min { |a, b| a.length <=> b.length }
    min.length.times.count { |i| str_a[i] != str_b[i] }
  end