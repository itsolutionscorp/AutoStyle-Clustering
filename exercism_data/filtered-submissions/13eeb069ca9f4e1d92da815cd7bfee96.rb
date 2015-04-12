class Hamming
  def compute(a, b)
    a.each_char.with_index.count {|char, index| char != b[index] }
  end
end
