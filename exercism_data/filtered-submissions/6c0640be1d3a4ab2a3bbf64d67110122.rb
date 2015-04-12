class Hamming
  def compute(a, b)
    delta = 0
    a.length.times do |i|
       delta += 1 if b[i] != a[i]
    end
    delta
  end
end
