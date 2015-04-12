class Hamming
  def compute(a,b)
    h = 0
    [a.length, b.length].min.times {|x| h += 1 if a[x] != b[x]}
    h
  end
end
