class Hamming
  def self.compute(strand_one, strand_two)
    @hamming_distance = 0
    @integer = 0

    split_strands(strand_one, strand_two)

    find_shortest_strand.times do
      calculate_hamming
    end
    @hamming_distance
  end

  def self.calculate_hamming
    if @strand_one_letters[@integer] != @strand_two_letters[@integer]
      @hamming_distance = @hamming_distance +=1
    end
    @integer +=1
  end

  def self.split_strands(strand_one, strand_two)
    @strand_one_letters, @strand_two_letters = []
    @strand_one_letters, @strand_two_letters = strand_one.split(""), strand_two.split("")
  end

  def self.find_shortest_strand
    if @strand_one_letters.count < @strand_two_letters.count
      shortest_strand_count = @strand_one_letters.count
    else
      shortest_strand_count = @strand_two_letters.count
    end
  end
end
