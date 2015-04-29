def compute(a, b)
    distance = 0
    a.chars.to_a.each_with_index do |symbol, index|
      distance += 1 if b[index] != symbol
    end
    return distance
  end