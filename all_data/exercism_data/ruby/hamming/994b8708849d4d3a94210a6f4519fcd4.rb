class Hamming
  def self.compute(first, second)
    (0...[first.length, second.length].min).each.count { |i| first[i] != second[i]  }
  end
end
