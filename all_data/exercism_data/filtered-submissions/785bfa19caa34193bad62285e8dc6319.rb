def compute(a, b)
    return 0 if a.size == 0 || b.size == 0

    count = [a.size, b.size].min - 1
    (0..count).inject(0) do |sum, i|
      a[i] != b[i] ? sum + 1 : sum
    end
  end