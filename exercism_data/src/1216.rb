def compute s1, s2
    s1.each_char.with_index.count { |char, idx| s2[idx] && char != s2[idx] }
  end