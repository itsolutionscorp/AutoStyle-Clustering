def compute(a, b)

    hamming_distance = 0
    i = 0

      until i == a.length

        if a[i] != b[i]
        hamming_distance += 1
        end

      i += 1
      end

    return hamming_distance

  end