class DNA
  def initialize(strand)
    @strand = strand.chars.to_a
    @count = 0
  end

  def hamming_distance(comparison_strand)
    comparison_strand = comparison_strand.chars.to_a
    (0).upto(comparison_strand.size).each do |index|
      return @count if comparison_strand[index].nil? || @strand[index].nil?
      unless comparison_strand[index] == @strand[index]
        @count += 1
      end
    end
  end
end
