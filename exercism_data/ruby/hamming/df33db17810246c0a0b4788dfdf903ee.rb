class Hamming
  def self.compute(strand_1, strand_2)
    strand_1.each_char.with_index.count {|letter, index| letter != strand_2[index]}
  end
end
