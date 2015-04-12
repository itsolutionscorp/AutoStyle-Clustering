class Hamming
  def compute(a, b)
    a.chars.zip(b.chars).select { |pair| pair.first != pair.last }.count
  end
end
