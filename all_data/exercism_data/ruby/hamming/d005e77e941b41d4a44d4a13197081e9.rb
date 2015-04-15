class Hamming

  def self.compute(*strands)
    strands = strands.map(&:chars)
    strands.sort_by!(&:length)
    diff_count = 0
    strands[0].each.with_index do |nucleotide, index|
      diff_count += 1 if nucleotide != strands[1][ index ]
    end
    return diff_count
  end

end
