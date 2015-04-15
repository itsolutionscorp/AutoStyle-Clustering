class Hamming
  def self.compute(a,b)
    distance = 0
    a.chars.each_with_index.map { |c, i|
      distance = distance + 1 unless c == b.chars[i]
    }
    distance
  end

end
