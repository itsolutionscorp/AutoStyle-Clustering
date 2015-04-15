class Hamming
  def self.compute(first, second)
    second.chars.each_with_index.map do |char, index|
      first.chars[index] == char ? 0 : 1
    end.reduce(:+)
  end
end
