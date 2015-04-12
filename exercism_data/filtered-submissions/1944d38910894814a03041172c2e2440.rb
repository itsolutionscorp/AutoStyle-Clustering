class Hamming
  def compute(x, y)
    length = [x.length, y.length].max
    diff = 0
    (0...length).each do |i|
      if x[i] != y[i]
        diff += 1
      end
    end
    diff
  end
end
