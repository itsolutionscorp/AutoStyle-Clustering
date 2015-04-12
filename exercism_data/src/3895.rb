def compute(a, b)
    raise DifferingArgumentLength unless a.length == b.length
    distance = 0
    (0..(a.length - 1)).each do |idx|
      distance += 1 if a[idx] != b[idx]
    end
    distance