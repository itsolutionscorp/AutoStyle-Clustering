class Hamming
  def self.compute(a, b)
    (a^b).to_s(2).count("1")
  end
end
