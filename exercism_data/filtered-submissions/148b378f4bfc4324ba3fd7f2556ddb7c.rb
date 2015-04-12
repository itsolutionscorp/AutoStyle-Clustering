class Hamming
  def compute(strand_1, strand_2)
    strand_1.length.times.count do |i|
      strand_1[i] != strand_2[i]
    end
  end
end
