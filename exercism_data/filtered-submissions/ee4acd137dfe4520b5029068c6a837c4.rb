class Hamming
  def compute(x, y)
    distance = 0
    x.chars.each_index do |i|
      distance += 1 unless x[i] == y[i]
    end
    distance
  end
end
