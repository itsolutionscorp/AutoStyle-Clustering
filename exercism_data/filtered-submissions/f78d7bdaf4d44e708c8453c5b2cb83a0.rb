class Hamming
  def compute(left, right)
    if left.length > right.length
      left, right = right, left
    end
    diff = left.chars.zip(right.chars).map {|a| a[0]==a[1]}
    diff.count(false)
  end
end
