def compute(string_a, string_b)
      0.upto(string_a.size).count { |i| string_a[i] != string_b[i] }
  end