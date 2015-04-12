def compute(first_strand, second_strand)
    @hamming_distance = 0
    [first_strand.chars, second_strand.chars].transpose.each do |first_strand_base, second_strand_base|
      if !first_strand_base.eql?(second_strand_base)
        @hamming_distance += 1 
      end
    end
    return @hamming_distance
  end