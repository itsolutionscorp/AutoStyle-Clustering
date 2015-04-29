class Hamming

  def self.compute(strand1, strand2)
    self.set_variables_with(strand1, strand2)
    hamming_distance = @strand_arr1.each_with_index.inject(0) do |memo, (nucleotide, index)|
      memo += 1 if nucleotide != @strand_arr2[index]
      memo
    end
  end

  private

  def self.set_variables_with(strand1, strand2)
    @test_length = self.find_min_length(strand1, strand2)
    @strand_arr1 = self.strand_array(strand1)
    @strand_arr2 = self.strand_array(strand2)
  end

  def self.strand_array(strand)
    strand[0...@test_length].chars.to_a
  end

  def self.find_min_length(s1, s2)
    [s1.length, s2.length].min
  end

end
