module Hamming

  module_function

  def compute(x, y)
    distance = [x.length, y.length].min - 1
    compare(x, y, distance)
  end

  def compare(x, y, distance)
    differences = 0
    (0..distance).each do |i|
      differences += 1 unless x[i] == y[i]
    end
    differences
  end
end
