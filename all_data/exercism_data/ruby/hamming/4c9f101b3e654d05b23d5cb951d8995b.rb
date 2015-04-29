class Hamming

  def self.compare(a, b)
    return 0 if a == b
    return 1
  end

  def self.compute(str1, str2)
    count = 0
    str1 = str1.split('')
    str2 = str2.split('')
    str1.map.with_index { |l, i| count += compare(l, str2[i]) }
    count
  end
end
