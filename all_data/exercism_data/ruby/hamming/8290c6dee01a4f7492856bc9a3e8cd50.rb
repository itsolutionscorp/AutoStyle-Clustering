class Hamming
  def self.compute(base, compare)
    count = 0
    compare.chars.each_with_index { |char, index| count += 1 unless char == base[index] }
    count
  end
end
