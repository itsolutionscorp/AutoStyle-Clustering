class Hamming
  def compute(left, right)
    left.bytes.zip(right.bytes).count do |item|
      item.all? && item[0] != item[1]
    end
  end
end
