class Hamming
  def self.compute(strand_1, strand_2)
    distance = 0
    strand_1.chars.each_with_index do |c, index|
      distance += 1 if c != strand_2.to_s.chars[index]
    end
    distance
  end
end
