class Hamming
  def compute(a_string, b_string)
    a_chars = a_string.chars
    b_chars = b_string.chars

    a_chars.zip(b_chars).count do |a,b|
      a != b
    end
  end
end
