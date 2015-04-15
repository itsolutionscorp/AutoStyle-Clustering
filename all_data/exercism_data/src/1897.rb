def compute x, y
    size = x.size > y.size ? y.size : x.size
    return x.split('').first(size).zip(y.split('').first(size)).select {|a,b| a != b}.size
  end