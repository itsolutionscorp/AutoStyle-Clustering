class Hamming
  def compute a, b
    pairs = a.chars.zip(b.chars).reject{|x,y| x.nil? || y.nil? }
    pairs.select{|x,y| x != y}.length
  end
end
