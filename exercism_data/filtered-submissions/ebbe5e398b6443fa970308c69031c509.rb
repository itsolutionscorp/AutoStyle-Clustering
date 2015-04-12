def compute(first_range, second_range)
    first_range.chars.each_with_index.count do |char, index|
      second_range[index] != char
    end
  end