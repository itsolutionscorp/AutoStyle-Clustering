class Hamming
  def compute(a, b)
    a, b = b, a if a.size > b.size

    a.chars.zip(b.chars).count {|x,y| x != y}
  end
end
