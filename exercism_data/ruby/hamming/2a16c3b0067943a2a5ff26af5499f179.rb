class Hamming
  def self.compute(a, b)
    (0...a.length).inject(0) { |hamming, n| hamming + (a[n] != b[n] ? 1 : 0) }
  end
end
