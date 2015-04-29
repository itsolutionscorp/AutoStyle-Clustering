class Hamming
  def self.compute(a, b)
    len = a.length <= b.length ? a.length : b.length
    count = 0
    (0...(len)).each do |i|
      count += 1 unless a[i] == b[i]
    end
    count
  end
end
