def compute(a, b)
    a[0, b.size].chars.zip(b.chars).count { |n| !n[0].eql? n[1] }
  end