class Hamming
  def compute(a, b)
    d = 0    

    l = a.length < b.length ? a.length : b.length
    l.times { |i| d += 1 if a[i] != b[i] }

    d
  end
end
