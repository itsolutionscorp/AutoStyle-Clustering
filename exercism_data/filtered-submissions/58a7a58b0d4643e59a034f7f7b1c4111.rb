def compute(a, b)
    n = 0
    a.chars.each_with_index do |char, index|
      return n if index == b.length
      char == b.chars[index] ? n : n=n+1
    end
    n
  end