def compute(sample1, sample2)
    result = 0

    s1 = sample1.chars()
    s2 = sample2.chars()

    i = s1.length()
    if s1.length() > s2.length()
      i = s2.length
    end


    until i == 0
      if s1[i - 1] != s2[i - 1]
        result += 1
      end
      i -=1
    end

    result
  end