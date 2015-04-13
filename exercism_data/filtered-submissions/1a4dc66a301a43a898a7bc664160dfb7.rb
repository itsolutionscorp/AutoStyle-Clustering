def compute(a, b)
    if a.length < b.length
      a.each_char.zip(b.each_char).count { |x, y| x != y }
    else
      b.each_char.zip(a.each_char).count { |x, y| x != y }
    end