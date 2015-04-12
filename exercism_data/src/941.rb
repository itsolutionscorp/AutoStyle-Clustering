def compute(string1, string2)
    stringlength = (string1.size, string2.size).min
    (0...stringlength).count { |x| string1[x] != string2[x] }
  end