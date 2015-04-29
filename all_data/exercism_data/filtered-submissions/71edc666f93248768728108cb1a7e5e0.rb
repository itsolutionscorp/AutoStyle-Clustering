def compute(strand_a, strand_b)
    distance   = 0
    min_length = [strand_a.length, strand_b.length].min
    for i in 0..(min_length -1) do
      distance += strand_a[i].chr == strand_b[i].chr ? 0:1
    end
    distance
  end