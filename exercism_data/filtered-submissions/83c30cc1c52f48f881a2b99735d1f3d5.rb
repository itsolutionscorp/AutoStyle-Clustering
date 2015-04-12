class Hamming

  def compute a, b
    a.chars[0, b.size].zip(b.chars).count { |char_a,char_b| char_a != char_b }
  end

end
