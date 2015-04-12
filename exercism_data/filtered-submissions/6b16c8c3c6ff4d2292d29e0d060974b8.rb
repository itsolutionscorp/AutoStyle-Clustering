class Hamming
  def compute(a,b)
    [a.split(//).length, b.split(//).length].min.times.count{ |i|  a[i] != b[i] }
  end
end
