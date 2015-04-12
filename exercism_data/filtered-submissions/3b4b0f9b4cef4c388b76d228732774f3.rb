module Hamming
  def compute(string_a, string_b)
    paired_chars = string_a.each_char.lazy.zip(string_b.each_char)
    paired_chars.count { |char_a, char_b| char_a != char_b }
  end
end
