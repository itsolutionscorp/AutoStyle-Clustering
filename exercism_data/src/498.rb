def compute(s, t)
    result = 0

    len = [s.length, t.length].min - 1

    for i in 0..len
      if(s[i] != t[i])
        result += 1
      end
    end

    result
  end