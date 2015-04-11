class Hamming

  def self.compute(a, b)
    distance, i = 0, 0
    until done?(a, b, i)
      distance += 1 if a[i] != b[i]
      i += 1
    end
    distance
  end

  private

  def self.done?(a, b, i)
    a[i].nil? || b[i].nil?
  end
end
