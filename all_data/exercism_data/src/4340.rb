class Hamming
  def compute(strand_1,strand_2)
    h_dist = 0
    strand_1.each_char.with_index do |dna,i|
      h_dist += 1 if dna != strand_2[i]
    end
    h_dist
  end
end
