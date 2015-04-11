class Hamming
  def self.compute(a, b)
    n = [a.length, b.length].min
    diff = 0
    [0...n].each do |i|
      if (a[i] != b[i])
        diff += 1
      end
    end
    return diff
  end
end
