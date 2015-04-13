def compute(s1, s2)
    s1_chars = s1.chars
    s2_chars = s2.chars
    hamming = 0


    len = s1_chars.length > s2_chars.length ? s2_chars.length : s1_chars.length
    len -= 1

    for i in 0..len

      if s1_chars[i] != s2_chars[i]
        hamming += 1
      end
    end

    hamming
  end