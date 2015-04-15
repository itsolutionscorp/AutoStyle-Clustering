class Hamming

  def self.compare(a, b)
    return a == b ? 0 : 1
  end

  def self.compute(str1, str2)
    count = 0
    str1.chars.map.with_index { |l, i| count += compare(l, str2[i]) }
    count
  end
end
