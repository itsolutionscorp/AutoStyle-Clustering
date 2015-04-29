def compute(a, b)
    hamming = 0
    a.length.times do |i|
      if a[i,1] != b[i,1]
        hamming += 1
      end
    end
    hamming
  end