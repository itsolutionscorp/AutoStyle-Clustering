class Hamming
  def self.compute(a, b)
    a, b = b, a if a.length > b.length

    distance = 0

    0.upto(a.length - 1) do |i|
      distance += 1 unless a[i] == b[i]
    end

    distance
  end
end
