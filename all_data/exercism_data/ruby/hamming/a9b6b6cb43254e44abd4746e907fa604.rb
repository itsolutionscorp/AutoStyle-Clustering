class Hamming

  def self.compute(strand_one, strand_two)
    count = 0

    strand_one.split("").each_with_index do |e,i|
      count += 1 if strand_one[i] != strand_two[i]
    end

    count
  end

end
