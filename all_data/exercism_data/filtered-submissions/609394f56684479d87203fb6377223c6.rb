def compute(arg1, arg2)

    i = 0
    hamming_distance = 0
    while i < arg1.length
      if arg1[i] != arg2[i]
        hamming_distance += 1
      end
      i += 1
    end
    hamming_distance
  end