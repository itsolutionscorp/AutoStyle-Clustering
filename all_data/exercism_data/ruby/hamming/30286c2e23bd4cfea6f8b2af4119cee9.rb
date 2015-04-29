class DNA < Struct.new(:strand)
  def hamming_distance(other)
    HammingDistance.new(strand, other).size
  end
end

class HammingDistance < Struct.new(:strand, :other)
  def size
    difference.size
  end

  def difference
    shortest.chars.each_with_index.reject do |nucleotide, index|
      nucleotide == longest[index]
    end
  end

  private

  def shortest
    by_length.first
  end

  def longest
    by_length.last
  end

  def by_length
    [strand, other].sort_by(&:length)
  end
end
