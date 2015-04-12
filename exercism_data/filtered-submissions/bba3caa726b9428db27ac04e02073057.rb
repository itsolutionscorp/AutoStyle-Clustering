# Can't fetch next exercise, seeing if this works.

module Hamming
  def compute(a, b)
    short_strand, long_strand = [a, b].map(&:chars).sort_by &:size

    short_strand.each_with_index.count do |acid, index|
      long_strand[index] != acid
    end
  end
end
