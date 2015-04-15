def compute(str1, str2)

    a1 = str1.chars
    a2 = str2.chars
    a1.each_index {|i| if a1[i] != a2[i] then score += 1 end}

  end