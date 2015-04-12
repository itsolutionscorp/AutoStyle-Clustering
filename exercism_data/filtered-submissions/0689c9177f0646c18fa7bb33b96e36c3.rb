class Hamming
  def compute(strand_1, strand_2)
    (0..strand_1.length).inject(0) do |sum, i|
      strand_1[i] != strand_2[i] ? sum + 1 : sum
    end
  end
end
