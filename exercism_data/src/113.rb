def compute(s1, s2)
    return unless s1 || s2

    s2_chars = s2.chars

    s1.chars.each.with_index.inject(0) do |sum, (s1_char, index)|
      return sum unless s2_char = s2_chars[index]
      sum += 1 if s1_char != s2_char
      sum
    end
  end