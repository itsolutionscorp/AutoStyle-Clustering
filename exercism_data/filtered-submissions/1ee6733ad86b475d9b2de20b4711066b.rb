class Hamming
  def compute(string1, string2)
    string1.chars.zip(string2.chars).count {|x, y| x != y }
  end
end
