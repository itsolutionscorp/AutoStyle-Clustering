def compute(str1, str2)
    score = 0
    0.upto(str1.length) { |i| score += 1 if str1[i] != str2[i] }
    score
  end