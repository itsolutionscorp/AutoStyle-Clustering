class Hamming

  def self.compute(strand_one, strand_two)

    if(strand_one.length > strand_two.length)
      strand_one = strand_one[0..strand_two.length-1]
    end

    differences = 0

    strand_one.length.times do |i|
      if(strand_one[i] != strand_two[i])
        differences = differences + 1
      end
    end

    differences
  end

end
