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
    ret = 0
    [a.length, b.length].min.times do |index|
      ret += 1 if a[index] != b[index]
    end
    ret
  end
end
