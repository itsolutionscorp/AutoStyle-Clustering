def compute(a, b)
    b = b.bytes
    res = 0
    a.bytes.each_with_index {|c, i| res+= 1 if c != b[i]}
    res
  end
end