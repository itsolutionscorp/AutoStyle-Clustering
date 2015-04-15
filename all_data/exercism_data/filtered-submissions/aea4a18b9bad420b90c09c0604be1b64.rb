def compute (string1, string2)
    st1, st2 = string1, string2
    index, distance = 0, 0

    while st1[index] && st2[index]
      distance += 1 if st1[index] != st2[index]
      index += 1
    end
    distance
  end