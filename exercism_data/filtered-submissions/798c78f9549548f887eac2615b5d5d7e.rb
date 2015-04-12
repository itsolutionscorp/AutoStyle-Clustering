module Hamming
  def compute(a, b)
    return 0 if a == b
    y = b.split('')
    x = a.split('').take(y.size)
    result = x.map.with_index { |value, index| (value <=> y[index]).abs }
    result.inject(&:+)
  end
end
