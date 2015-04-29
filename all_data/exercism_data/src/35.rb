def compute(a, b)
    dif = 0
    a.chars.each_index do |i|
      dif += 1 if a[i] != b[i]
    end
    dif
  end