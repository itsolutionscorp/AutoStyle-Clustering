class Hamming
  def compute(first_strand, second_strand)
    @hamming_distance = 0
    [first_strand.chars, second_strand.chars].transpose.each do |first_base, second_base|
      if !first_base.eql?(second_base)
        @hamming_distance += 1 
      end
    end
    return @hamming_distance
  end
end
