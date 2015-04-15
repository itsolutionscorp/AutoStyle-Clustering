def compute a, b
    a.chars.each_with_index.reduce(0) do |sum, (char, index)|
      sum + (char == b.chars[index] ? 0 : 1)
    end
  end