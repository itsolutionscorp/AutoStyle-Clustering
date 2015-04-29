class Hamming
  def self.compute(a,b)
    b_chars = b.each_char.to_a
    a.chars.select.with_index do |char, index|
      b_chars[index] != nil && char != b_chars[index]
    end.length
  end
end
