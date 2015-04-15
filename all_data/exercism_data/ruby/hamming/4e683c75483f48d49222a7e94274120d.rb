class Hamming

  def self.compute(a, b)
    [a.length, b.length].min.times.count do |index|
      a[index] != b[index]
    end
  end

end
