def compute(s1, s2)
    count = 0
    s1.chars.each_with_index { |char, i| count += 1 if char != s2[i] }
    return count
  end