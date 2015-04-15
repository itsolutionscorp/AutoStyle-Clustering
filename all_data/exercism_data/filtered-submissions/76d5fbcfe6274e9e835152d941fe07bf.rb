def compute(s1, s2)
    i, ham = 0, 0
      while i < s1.length
        if s1[i] != s2[i]
          ham += 1
        end
        i += 1
      end
    ham
  end