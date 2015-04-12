class Hamming
  def compute(a, b)
    a.chars.zip(b.chars).select { |a,b| a && b }.count { |a,b| a != b }
  end
end
