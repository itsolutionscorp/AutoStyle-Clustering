class Hamming
  def self.compute (from, to)
    (0...[from.length, to.length].min).each.count { |i| from[i] != to[i] }
  end
end
