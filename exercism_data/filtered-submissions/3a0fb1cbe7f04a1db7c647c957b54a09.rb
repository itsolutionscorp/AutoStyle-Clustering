def compute(first, second)
    distance = 0
    first.chars.zip(second.chars).each do |a, b|
      distance = distance + 1 unless a == b
    end
    distance
  end