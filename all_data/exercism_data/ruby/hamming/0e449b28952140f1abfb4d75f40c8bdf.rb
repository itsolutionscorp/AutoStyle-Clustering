class Hamming
  def self.compute(a, b)
    a.chars.map.with_index{|val, i| (val <=> b.chars[i]).abs }.inject(:+)
  end
end
