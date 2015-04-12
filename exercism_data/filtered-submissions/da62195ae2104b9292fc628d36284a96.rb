class Hamming
  def compute(x, y)
    x.length.times.inject(0) do |acc, i|
      acc + (x[i] == y[i] ? 0 : 1)
    end
  end
end
