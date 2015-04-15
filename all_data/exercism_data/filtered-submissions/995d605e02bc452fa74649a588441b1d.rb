def compute(strand, another_strand)
    if strand == another_strand
      0
    else
      last_index = [strand.length, another_strand.length].min - 1
      (0..last_index).to_a.inject(0) do |result, index|
        if strand[index] != another_strand[index]
          result + 1
        else
          result
        end
      end
    end
  end