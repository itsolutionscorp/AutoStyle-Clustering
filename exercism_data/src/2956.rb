class Hamming
  def compute(a, b)
    lists_as_pairs = a.chars.zip(b.chars)

    actual_pairs = lists_as_pairs.reject {|pair| pair.include?(nil)}

    distance = actual_pairs.count {|pair| pair.first != pair.last }
  end
end
