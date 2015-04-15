class Hamming

  def Hamming.compute( first_strand, second_strand )
    return 0 if first_strand.empty? or second_strand.empty?
    hamming_distance = 0
    length = self.shortest_strand_length( first_strand, second_strand )
    first_strand.chars.each_with_index do |nucleotide, i|
      break if i >= length
      if second_strand.chars[i] != nucleotide
        hamming_distance += 1
      end
    end
    hamming_distance
  end

  private
    def Hamming.shortest_strand_length( first_strand, second_strand )
      return first_strand.length if first_strand.length < second_strand.length
      second_strand.length
    end


end
