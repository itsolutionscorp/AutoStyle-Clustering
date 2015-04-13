def compute(s, t)



    if (s.length < t.length)
      a_str = s
      b_str = t
    else
      a_str = t
      b_str = s
    end

    hamming = 0
    a_str.each_char.with_index do |a_char, i|
      b_char = b_str[i]

      hamming += 1 if (a_char != b_char)
    end

    hamming
  end