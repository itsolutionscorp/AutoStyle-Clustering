class Hamming
  def self.compute(a, b)
    if a.eql?(b)
      0
    else
      difference(a, b)
    end
  end

protected
  def self.difference(a, b)
    [a.length, b.length].min.times.inject(0) do |result, index|
      result += 1 if a[index] != b[index]
      result
    end
  end
end
