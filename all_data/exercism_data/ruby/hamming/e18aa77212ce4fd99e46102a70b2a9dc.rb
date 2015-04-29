class Hamming
  def self.compute(a, b)
    (0...a.length).inject(0) { |sum,i| !(a[i] == b[i]) ? sum + 1 : sum }
  end
end
