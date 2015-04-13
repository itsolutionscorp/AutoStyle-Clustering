def compute(a,b)
    return 0 if a == b
    count = 0
    a,b = b,a if a.size > b.size
    (0... a.size).each do |ind|
      count += 1 if a[ind] != b[ind]
    end
    return count
  end