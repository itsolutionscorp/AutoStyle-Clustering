def compute(a,b)
    [a.length, b.length].min.times.map do |i|
      a[i] != b[i]
    end.select {|el| el}.count
  end