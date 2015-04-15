class Hamming
  def self.compute(strand_1, strand_2)
    shortest_strand = find_shortest_strand(strand_1, strand_2)

    counter = 0
    hamming = 0
    shortest_strand.length.times do
      if strand_1[counter] != strand_2[counter]
        hamming +=1
      end
      counter +=1
    end
    hamming
  end

  private

  def self.find_shortest_strand(strand_1, strand_2)
    if strand_1.length > strand_2.length
      strand_2
    else
      strand_1
    end
  end
end
