def compute(a, b)
    differences = 0
    a.chars.each_with_index do |char, index|
      differences += 1 unless char == b[index] || b[index].nil?
    end
    differences
  end