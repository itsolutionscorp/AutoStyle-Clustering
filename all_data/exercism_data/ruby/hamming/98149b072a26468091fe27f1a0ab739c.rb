class DNA
  attr_reader :strand

  def initialize(strand)
    @strand = strand
    @result = 0
  end

  def hamming_distance(other_strand)
    @strand.chars.each_index do |index|
      if other_strand.chars[index] != @strand.chars[index]
        @result += 1
      end
    end
    return @result
  end
end
