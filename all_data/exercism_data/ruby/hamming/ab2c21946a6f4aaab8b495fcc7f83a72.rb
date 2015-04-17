class Hamming
  def self.compute(x, y)
    res = 0
    return res if x == y
    x.chars.each_with_index { |xi, i| res += 1 if xi != y[i] }
    res
  end
end