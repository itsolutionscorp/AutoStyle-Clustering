class Hamming
  def self.compute(string_a, string_b)
    unmatched_count = 0
    string_a.chars.each_with_index do |char_a, index|
      unmatched_count += 1 if char_a != string_b.chars[index]
    end
    unmatched_count
  end
end
