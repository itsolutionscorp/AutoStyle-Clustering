class Hamming
  def compute(left, right)
    left_bytes = left.bytes.each
    right_bytes = right.bytes.each

    total = 0
    left_bytes.each { |num| total += 1 if num != right_bytes.next }
  ensure
    return total
  end
end
