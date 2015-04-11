class Hamming
  def self.compute(strand_1, strand_2)
    mutations = 0

    strand_1.chars.each_with_index do |val, index|
      mutations += 1 if strand_2.chars[index] != val
    end
    mutations
  end
end
