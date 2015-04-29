def compute(s1, s2)
    iterations = s1.length < s2.length ? s1.length : s2.length

    hd = 0
    for i in 0...iterations
      hd += 1 if s1[i] != s2[i]
    end

    hd
  end