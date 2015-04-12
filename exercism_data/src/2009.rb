class Hamming

  def compute(a, b)
    c = a.chars.zip b.chars
    c.count {|elem| elem == elem.uniq && !elem.include?(nil)}
  end
end
