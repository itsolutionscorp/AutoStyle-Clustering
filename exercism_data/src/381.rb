def compute(stand_a, stand_b)
    (0...[stand_a.length, stand_b.length].min).count { |num| stand_a[num] != stand_b[num] }
  end