class Hamming
  def compute(sample1, sample2)
    result = 0

    s1 = sample1.chars()
    s2 = sample2.chars()

    i = s1.length()
    if s1.length() > s2.length()
      i = s2.length
    end
    # determining the length of the shortest string

    until i == 0 #iterating through the strings from the last common position
      if s1[i - 1] != s2[i - 1]
        result += 1 #add a mismatch to the total count
      end
      i -=1
    end

    result
  end
end
