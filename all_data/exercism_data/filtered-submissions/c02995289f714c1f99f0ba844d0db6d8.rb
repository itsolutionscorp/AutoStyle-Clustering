def compute(a, b)
    length = [a.length, b.length].min
    count = 0
    (0..(length-1)).each do |i|
      count += 1 if a[i] != b[i]
    end
    count
  end