def compute(s1, s2)
    distance = 0
    s1.each_char.with_index do |item, idx|
      break unless idx < s2.length
      distance += 1 if item != s2[idx]
    end
    distance
  end