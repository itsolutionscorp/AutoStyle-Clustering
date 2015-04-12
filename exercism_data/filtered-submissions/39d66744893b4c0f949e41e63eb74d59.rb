class Hamming
  def compute(strand_1, strand_2)
    strand_1.length.times.count do |index|
      strand_1[index] != strand_2[index]
    end
  end
end
