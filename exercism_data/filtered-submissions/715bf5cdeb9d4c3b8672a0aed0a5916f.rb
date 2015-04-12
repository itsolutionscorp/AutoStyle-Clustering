def compute(a, b)
    [a.chars, b.chars].min_by(&:size).each_with_index.map do |_, i|
      (a.chars[i] <=> b.chars[i]).abs
    end.inject(:+)
  end