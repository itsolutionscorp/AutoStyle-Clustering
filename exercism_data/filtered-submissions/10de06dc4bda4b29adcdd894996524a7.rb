class Hamming
  def compute(first, second)
    return -1 if first.length != second.length
    first, second = first.chars, second.chars
    first.zip(second).count { |elem| elem.first.downcase != elem.last.downcase }
  end
end
