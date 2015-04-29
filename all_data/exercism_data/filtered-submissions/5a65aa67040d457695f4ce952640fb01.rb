def compute a, b
    result = 0
    a.chars.each_with_index do |c, i|
      result += 1 if c != b[i]
    end
    result
  end