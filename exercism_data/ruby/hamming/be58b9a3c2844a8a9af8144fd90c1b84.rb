class Hamming
  def self.compute(strand_a, strand_b)
    strand_a = strand_a.split('')
    strand_b = strand_b.split('')

    mutations = 0

    strand_a.each_index do |index|
      mutations += 1 if strand_a[index] != strand_b[index]
    end

    hamming_difference = mutations
  end
end
