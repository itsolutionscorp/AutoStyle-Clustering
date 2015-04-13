def compute(a,b)
    a = a.split('')
    b = b.split('')
    diff = a.map.with_index {|x, idx| x != b[idx]}.select {|x| x}
    diff.length
  end