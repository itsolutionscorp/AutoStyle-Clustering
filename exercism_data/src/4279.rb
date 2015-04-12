class Hamming
  def compute(a,b)
    [a.length, b.length].min.times.count { |i| not a[i].eql? b[i]}
  end
end
