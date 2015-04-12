class Hamming
  def compute(string_a, string_b)
    distance = 0
    string_a.each_char.with_index { |char, i| distance += 1 if char != string_b[i] }
    distance
  end
end
