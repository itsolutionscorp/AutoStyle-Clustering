class Hamming
  def compute(a, b)
    a.split(//).each_with_index.map{|c, i|
      c == (b[i] || c) ? 0 : 1
    }.inject(&:+)
  end
end
