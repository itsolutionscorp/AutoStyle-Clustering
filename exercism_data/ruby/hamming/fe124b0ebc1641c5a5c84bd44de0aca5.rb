class Hamming
  def self.compute(a, b)
    length = [a.length, b.length].min
    differences = 0

    for i in (0..length)
      differences += 1 if a[i] != b[i]
    end

    return differences
  end
end
