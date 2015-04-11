class Hamming
  def self.compute(a, b)
    a.length < b.length ? len = a.length : len = b.length
    len -= 1 # Correlate lenght with array position
    cnt = 0
    (0..len).each do |i|
      cnt += 1 if a.bytes[i] != b.bytes[i]
    end
    cnt
  end
end
