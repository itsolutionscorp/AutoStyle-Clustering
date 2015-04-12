class Hamming
  def compute(strand_1, strand_2)
    (0..strand_1.length).count { |index| strand_1[index] != strand_2[index] }
  end
end
