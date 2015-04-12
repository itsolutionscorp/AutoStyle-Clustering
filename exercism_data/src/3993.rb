def compute(a,b)
    return -1 if a.length != b.length
      d = 0
      (a.length).times do |i|
        d+=1 if a[i] != b[i]
      end
      d
    end