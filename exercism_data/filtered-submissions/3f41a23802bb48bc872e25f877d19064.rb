def compute(l, r)
    [l.size, r.size].min.times.count { |i| l[i] != r[i] }
  end