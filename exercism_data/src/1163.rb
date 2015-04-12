class Hamming
  def compute(left, right)
    return 0 if left == right
    length = [left.length, right.length].min
    left[0...length].chars.zip(right[0...length].chars).select { |l,r| l != r }.count
  end
end
