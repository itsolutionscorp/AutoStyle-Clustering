def compute(a, b)
    total = 0
    a.each_char.with_index do |char, index|
      total += 1 if char != b[index]
    end
    total
  end