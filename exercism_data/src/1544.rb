def compute(s1, s2)
      s1 = s1.chars
      mutations = 0
      s2.chars.each_with_index do |c, i|
        return mutations if s1[i] == nil || c == nil   
        mutations += 1 if s1[i] != c
      end
      mutations
    end