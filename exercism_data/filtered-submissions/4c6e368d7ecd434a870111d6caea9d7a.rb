def compute a, b
    count = 0
    a.length.times do |i|
      count += 1 if a[i] != b[i]
    end
    count
  end