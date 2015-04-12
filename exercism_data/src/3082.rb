class Hamming
  def compute(a,b)
    distance = 0
    0.upto([a.length,b.length].min - 1) do |i|
      distance += 1 if a[i] != b[i]
    end
    distance
  end
end
