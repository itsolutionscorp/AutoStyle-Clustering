def compute(s1, s2)
    ret = 0
    for i in 0..([s1.length, s2.length].min - 1) do
      ret += 1 if s1[i] != s2[i]
    end
    ret
  end