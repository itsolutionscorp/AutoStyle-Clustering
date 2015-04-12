class Hamming
  def compute(a, b)
    min = [a.length, b.length].min
    (0...min).inject(0) { |diff, i| diff + (a[i] <=> b[i]).abs }
  end
end
