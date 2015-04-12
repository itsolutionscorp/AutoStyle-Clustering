class Hamming

  def compute(strand_1, strand_2)
    min_length = [strand_1.length, strand_2.length].min

    (0...min_length).reduce(0) do |hamming_difference, index|
      strand_1[index] == strand_2[index] ? hamming_difference : hamming_difference + 1
    end
  end

end
