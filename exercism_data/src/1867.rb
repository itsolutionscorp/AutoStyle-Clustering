class Hamming
  def compute(left, right)
    left.split('').zip(right.split('')).reduce 0 do |distance, (left, right)|
      distance + (left && right && left != right ? 1 : 0)
    end
  end
end
