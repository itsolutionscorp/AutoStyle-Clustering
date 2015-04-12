class Hamming
  def compute(a, b)
    a_chars = a.chars
    b_chars = b.chars
    diff = 0

    a_chars.each_with_index do |c, i|
      diff += 1 if b_chars[i] && b_chars[i] != c
    end
    diff
  end
end
