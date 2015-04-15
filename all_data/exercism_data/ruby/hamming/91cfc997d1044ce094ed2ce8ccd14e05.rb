class Hamming
  def self.compute( a, b )
    hamming_distance = 0
    [a.length,b.length].min.times do |i|
      hamming_distance += 1 if a[i] != b[i]
    end
    hamming_distance
  end
end
