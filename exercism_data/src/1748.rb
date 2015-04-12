class Hamming
  def compute(a, b)
    min_len = [a.length, b.length].min

    (0...min_len).count { |i| a[i] != b[i] }
  end
end
