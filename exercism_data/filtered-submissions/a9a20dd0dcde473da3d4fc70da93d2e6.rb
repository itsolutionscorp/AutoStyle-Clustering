class Hamming
  def compute(a, b)
    lists_as_pairs = a.chars.zip(b.chars)

    actual_pairs = lists_as_pairs.reject {|pair| pair.include?(nil)}

    nonidentical_pairs = actual_pairs.reject {|pair| pair.first == pair.last }

    distance = nonidentical_pairs.size
  end
end
