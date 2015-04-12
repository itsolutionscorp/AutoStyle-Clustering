class Hamming
  def compute(a,b)
    (0...[a.size, b.size].min).reduce(0) {|t, i| a[i]==b[i] ? t : t + 1 }
  end
end
