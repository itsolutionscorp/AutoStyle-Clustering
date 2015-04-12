class Hamming
  def compute(a, b)
    a.chars.zip(b.chars).map { |nuc| nuc[0] == nuc[1] ? 0 : 1 }.reduce(:+)
  end
end
