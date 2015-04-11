class DNA
  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(compare_strand)
    size = strand.size > compare_strand.size ? compare_strand.size : strand.size
    strand.chars.take(size).zip(compare_strand.chars.take(size)).inject(0) do |accum, (a, b)|
      accum += 1 if a != b
      accum
    end
  end

  private

  attr_reader :strand
end
