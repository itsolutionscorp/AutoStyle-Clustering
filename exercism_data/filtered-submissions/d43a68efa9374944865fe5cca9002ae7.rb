class Hamming
  def compute(a, b)
    a.chars.select.with_index {|char, index| char != b[index]}.length
  end
end
