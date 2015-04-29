def compute(str_a, str_b)
    (0...[str_a.length, str_b.length].min).count { |i| str_a[i] != str_b[i] }
  end