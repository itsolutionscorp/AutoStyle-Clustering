def compute (a, b)
    a = a.chars
    b = b.chars
    a = a.slice(0, b.count)
    a.zip(b).count { |x, y| x != y }
  end