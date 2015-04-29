class Hamming

  def self.compute(a, b)
    minlen = [a.length, b.length].min
    diff = 0
    (0..minlen-1).each do |i|
      diff += 1 unless a[i] == b[i]
    end
    diff
  end
end
