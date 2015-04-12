class Hamming
  def compute(strand_1, strand_2)
    shortest_strand = [strand_1.length, strand_2.length].min
    hamming = 0
    shortest_strand.times do |counter|
      if strand_1[counter] != strand_2[counter]
        hamming +=1
      end
    end
    hamming
  end
end
