class Hamming
  def compute a, b
    length = [a.size, b.size].min
    a, b = a[0, length], b[0, length]
    a.chars.zip(b.chars).count {|a, b| a != b }
  end
end
