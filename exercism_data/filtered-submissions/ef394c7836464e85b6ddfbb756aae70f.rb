class Hamming
  def compute(x, y)
    h = 0
    x.length.times do |i|
      h += 1 unless x[i] == y[i]
    end
    h
  end
end
