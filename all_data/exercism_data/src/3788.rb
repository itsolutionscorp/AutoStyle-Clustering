def compute(x, y)
    count = 0
    length = x.length - 1
    (0..length).each do |i|
      count += 1 if x[i] != y[i]
    end
    count
  end