class Hamming
  def self.compute(a, b)
    a.zip(b).map { |pair| pair.first == pair.last }.count { |match| !match }
  end
end
