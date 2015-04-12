class Hamming
  def compute(left, right)
    if left.length > right.length
      left, right = right, left
    end
    left.chars.zip(right.chars).count {|a| a[0]!=a[1]}
  end
end
