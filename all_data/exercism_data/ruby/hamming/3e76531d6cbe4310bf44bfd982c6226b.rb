module Hamming

  module_function

  def compute(x, y)
    distance =
      case x.length <=> y.length
      when 1, 0
        y.length - 1
      when -1
        x.length - 1
      end
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
