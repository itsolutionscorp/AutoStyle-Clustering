def compute(d1, d2)
    hd = 0
    (0 .. [d1.length, d2.length].max).each { |idx|
      next if d1[idx].nil? or d2[idx].nil?

      hd += 1 if d1[idx] != d2[idx]
    }

    hd
  end