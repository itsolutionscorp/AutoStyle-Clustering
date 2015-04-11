class Hamming
  def self.compute(str_a, str_b)
    hamming_diff = 0
    str_a = str_a[0...str_b.length]
    str_a.each_char.with_index { |ch, i| hamming_diff += 1 if ch != str_b[i] }
    hamming_diff
  end
end
