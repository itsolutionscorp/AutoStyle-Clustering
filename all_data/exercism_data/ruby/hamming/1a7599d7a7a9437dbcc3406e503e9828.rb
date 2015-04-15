class Hamming

  def self.compute(strand_1, strand_2)
    diff_count = 0
    return diff_count if strand_1 == strand_2

    strand_1.split(//).each_with_index do |letter, index|
      diff_count += 1 if strand_1[index] != strand_2[index]
    end

    diff_count
  end

end
