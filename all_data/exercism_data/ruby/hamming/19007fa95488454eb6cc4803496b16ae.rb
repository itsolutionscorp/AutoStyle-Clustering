class Hamming
  def self.compute(strand, other_strand)
    new(strand, other_strand).compute
  end

  def initialize(strand, other_strand)
    @strand = strand
    @other_strand = other_strand
  end

  def compute
    if @strand.empty? || @other_strand.empty?
      0
    else
      difference_in_current_letter +
        self.class.compute(@strand[1..-1], @other_strand[1..-1])
    end
  end

  private

  def difference_in_current_letter
    if @strand[0] == @other_strand[0]
      0
    else
      1
    end
  end
end
