def compute(s1, s2)
    distance = 0
    s1.chars.each_with_index do |char, idx|
      break         if char.nil? || s2[idx].nil?
      distance += 1 if char != s2[idx]
    end
    return distance
  end