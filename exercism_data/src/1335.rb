def compute(x, y)
    a = x.chars
    b = y.chars
    count = 0
    a.each_with_index { |v, i| count += 1 unless v.eql?(b[i]) }
    count
  end