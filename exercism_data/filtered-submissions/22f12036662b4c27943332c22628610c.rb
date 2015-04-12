class Hamming
  def compute(a, b)
    d = 0

    [a.length, b.length].min.times { |i| d += 1 if a[i] != b[i] }

    d
  end
end
