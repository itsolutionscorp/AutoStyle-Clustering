class Hamming

  def self.compute(a, b)
    hamming = 0
    shortest_length(a,b).times do |i|
      hamming += 1 unless a[i] == b[i]
    end
    hamming
  end

  private

  def self.shortest_length(a,b)
    [a.size, b.size].min
  end

end
