def compute(s1, s2)
    i = 0
    n = s1.length  # assuming len(s1) == len(s2); sequences are of equal length
    n_differences = 0

    while (i < n)
      if (s1[i] != s2[i])
        n_differences += 1
      end

      i += 1
    end

    return n_differences
  end