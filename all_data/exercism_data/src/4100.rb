def compute(left, right)
    left.chars.each_with_index.map do |char, index|
      rchar = right.chars.at(index)
      ((char == rchar) || rchar.nil?) ? 0 : 1
    end.reduce(&:+)
  end