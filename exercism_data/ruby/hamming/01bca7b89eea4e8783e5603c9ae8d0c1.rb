class Hamming
  def self.compute(a, b)
    a.chars.zip(b.chars).take_while { |i| i.last != nil }.count { |(f, l)| f != l }
  end
end
