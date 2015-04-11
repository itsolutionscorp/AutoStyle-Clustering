class DNA
  attr_reader :original_strand, :other_strand

  def initialize(original_strand)
    @original_strand = original_strand
  end

  def hamming_distance(other_strand)
    0.upto([original_strand.length, other_strand.length].min - 1).inject(0) do |count, index|
      count += 1 if original_strand[index].to_s != other_strand[index].to_s
      count
    end
  end
end
