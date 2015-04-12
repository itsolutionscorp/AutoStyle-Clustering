def compute(a, b)
    count = 0
    a.each_char.each_with_index do |char, i|
      count += 1 if char != b[i]
    end
    count
  end