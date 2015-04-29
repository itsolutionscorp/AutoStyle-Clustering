def compute(str_a, str_b)
    mismatch = 0

    str_a.size.times do |n| str_a[n]
     mismatch += 1 if str_a[n] != str_b[n]
    end
  mismatch
  end