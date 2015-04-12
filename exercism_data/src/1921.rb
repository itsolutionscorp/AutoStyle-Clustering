def compute(a, b)
    diff_count = 0
    for i in 0..(a.size < b.size ? a.size-1 : b.size-1) do
      if a[i] != b[i]
        diff_count = diff_count + 1
      end
    end
    diff_count
  end