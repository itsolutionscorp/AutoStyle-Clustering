def compute a, b

    length = [a.length, b.length].min
    hamming = 0
    length.times do |i|
      hamming += 1 if a[i] != b[i]
    end
    hamming
  end