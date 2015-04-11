class Hamming
  def self.compute(a, b)
    return nil if a.length != b.length
    a.each_char.with_index.count { |e, i| e != b[i] }
  end
end
