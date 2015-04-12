def compute(a, b)
    (0...a.size).count{ |i| a[i] != b[i] }
  end
end