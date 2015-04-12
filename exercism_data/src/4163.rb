class Hamming
  def compute(strand_a, strand_b)
    strand_a = strand_a.chars
    strand_b = strand_b.chars

    mutations = 0

    strand_a.zip(strand_b).each do |nucleotid_a, nucleotid_b|
      mutations += 1 if nucleotid_a != nucleotid_b
    end

    mutations
  end
end
