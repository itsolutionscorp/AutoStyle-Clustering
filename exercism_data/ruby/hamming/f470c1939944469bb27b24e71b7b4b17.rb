class Hamming

  def self.compute(a, b)
    a.each_char.zip(b.each_char).take([a.length, b.length].min).map do |(a, b)|
      a == b ? 0 : 1
    end.reduce(:+)
  end

end
