class Hamming
  def self.compute(strand1, strand2)
    mutations = 0
    strand1.chars.take(strand2.length).each_with_index do |point, index|
      mutations += 1 unless point == strand2[index]
    end
    mutations
  end
end
