class Hamming
  def self.compute a, b
    length = [a.length, b.length].min

    distance = 0;
    length.times do |i|
      distance += 1 if a[i] != b[i]
    end

    return distance;
  end
end
