def compute(strand, other_strand)
    strand = strand.scan(/\w/)
    other_strand = other_strand.scan(/\w/)
    strand.each.with_index.inject(0) do |memo, (char, index)|
      char == other_strand[index] ? memo : memo +=1
    end
  end