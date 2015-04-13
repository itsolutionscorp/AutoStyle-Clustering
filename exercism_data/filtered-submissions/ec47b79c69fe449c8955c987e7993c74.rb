def compute(str_a, str_b)
    return 0 if str_a == str_b

    mismatches = 0
    length = [str_a.size, str_b.size].min

    length.times do |n|
      mismatches += 1 if str_a[n] != str_b[n]
    end

    mismatches
  end