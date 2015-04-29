def compute(a, b)

    ret = 0
    for x in (0..a.length-1)
      ret += 1 unless a[x] == b[x]
    end
    ret
  end