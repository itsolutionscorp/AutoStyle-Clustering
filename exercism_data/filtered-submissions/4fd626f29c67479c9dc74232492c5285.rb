def compute(string_a, string_b)
    count = 0
    string_a.chars.each_with_index do |char, index|
      count += 1 unless char == string_b.chars[index]
    end
    count
  end