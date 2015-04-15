class Hamming
  def self.compute(a, b)
    n = [a.length, b.length].min

    n.times.map do |i|
      a[i] != b[i] ? 1 : 0
    end.inject(:+)
  end
end
