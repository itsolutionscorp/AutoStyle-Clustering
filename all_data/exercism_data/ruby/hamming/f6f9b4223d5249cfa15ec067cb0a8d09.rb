class Hamming
  def self.compute(a, b)
    a.chars.zip(b.chars).map { |pair| pair.first == pair.last }.count { |match| !match }
  end
end
