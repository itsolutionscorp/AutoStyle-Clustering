class Hamming
  def self.compute(a, b)
    distance = 0
    0.upto(a.length - 1) do |n|
      distance += 1 unless a[n] == b[n]
    end
    distance
  end
end
