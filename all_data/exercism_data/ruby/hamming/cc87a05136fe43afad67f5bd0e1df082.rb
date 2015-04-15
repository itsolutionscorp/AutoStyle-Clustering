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
    [a.length, b.length].min.times.count do |index|
      a[index] != b[index]
    end
  end
end
