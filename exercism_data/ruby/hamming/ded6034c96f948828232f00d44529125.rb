class Hamming
  def self.compute(a, b)
    a.chars.map.with_index { |char, index| b[index] == char }.count { |match| !match }
  end
end
