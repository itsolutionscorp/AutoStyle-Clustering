class Hamming
  def self.compute(a,b)
    return if a.length != b.length
    diffs = 0
    a.chars.each_with_index { |v,i| diffs += b[i] == v ? 0 : 1 }
    diffs
  end
end
