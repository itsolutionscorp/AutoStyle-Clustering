class Hamming
  def self.compute(a , b)
    (0...[a.length, b.length].min).count do |i|
      a[i] != b[i]
    end
  end
end
