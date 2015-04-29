class Hamming
  def self.compute(a, b)
    shortest = [a.length, b.length].min
    difference = 0
    (0..shortest-1).each do |i|
      unless a[i] == b[i]
        difference += 1
      end
    end
    return difference
  end
end
