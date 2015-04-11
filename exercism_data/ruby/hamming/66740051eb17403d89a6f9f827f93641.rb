class Hamming
  def self.compute(strand_1, strand_2)
    strand_1.each_char.with_index(0).inject(0) do |sum, char_with_index|
       char, index = *char_with_index
       sum += (char == strand_2[index]) ? 0 : 1
    end
  end
end
