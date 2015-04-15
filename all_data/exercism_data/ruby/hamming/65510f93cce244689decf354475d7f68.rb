class Hamming
  def self.compute(strand1, strand2)
    distance = 0
    strand1.chars.each_with_index do |n, i|
      distance += 1 if n != strand2.chars[i]
    end
    distance
  end
end
