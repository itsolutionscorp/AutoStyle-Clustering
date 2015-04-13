def compute(strand, another_strand)
    return 0 if strand == another_strand
    last_index = [strand.length, another_strand.length].min - 1
    (0..last_index).reduce(0) do |result, index|
      if strand[index] != another_strand[index]
        result + 1
      else
        result
      end
    end
  end