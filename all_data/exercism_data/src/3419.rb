def compute(a, b)        
    (0...[a.length, b.length].min).inject(0) {|distance, i| distance + (a[i] != b[i] ? 1 : 0) }
  end