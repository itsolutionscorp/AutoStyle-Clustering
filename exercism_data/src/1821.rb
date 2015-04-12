module Hamming
  def compute(strand_1, strand_2)
    mutations = 0
    shorter_strand = [strand_1.length, strand_2.length].min
    (0...shorter_strand).each do |base|
      mutations += 1 unless strand_1[base] == strand_2[base]
    end
    mutations
  end
end
