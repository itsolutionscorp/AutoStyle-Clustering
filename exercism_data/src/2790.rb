class Hamming
  def compute(first, second)
    first.chars.zip(second.chars).count{ |f, s| f != s }
  end
end
