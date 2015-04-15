def compute(a, b)
    len = [a.length, b.length].min
    ham = 0
    0.upto(len-1) do |i|
      if a[i] != b[i]
        ham += 1
      end
    end
    ham
  end