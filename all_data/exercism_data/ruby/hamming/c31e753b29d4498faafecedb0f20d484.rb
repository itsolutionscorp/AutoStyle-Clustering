class Hamming
  class<<self
    def compute(strand, another_strand)
      other_nucleotids = another_strand.chars.to_a
      strand.chars.each_with_index.count { |nucleotid, index| 
        ( other_nucleotid = other_nucleotids[index] ) && nucleotid != other_nucleotid }
    end
  end
  
end
