class Hamming
  def self.compute(strand_a, strand_b)
    return unless strand_a.length == strand_b.length
    mutations = 0
    strand_a.each_char.with_index do |nucleotide, index|
      mutations += 1 if nucleotide != strand_b[index]
    end
    mutations
  end
end
