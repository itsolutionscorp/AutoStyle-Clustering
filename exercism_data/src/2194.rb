class Hamming
  def compute(a,b)
    limit = [a.size,b.size].min
    (0...limit).each.count { |i| a[i] != b[i] }
  end
end
