def compute(x, y)
    x = x[0 .. y.length-1] if x.length > y.length
    y = y[0 .. x.length-1] if y.length > x.length
    x.each_char.with_index.count { |char, index| y[index] != char }
  end