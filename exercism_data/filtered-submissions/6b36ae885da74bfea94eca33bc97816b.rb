def compute(x, y)
    hamming_dist = 0
    i = 0

    while i < x.length

      if x[i] != y[i]
        hamming_dist += 1
      end

    i += 1
    end

    hamming_dist

  end