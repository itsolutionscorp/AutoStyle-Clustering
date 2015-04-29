class DNA < String
  def hamming_distance(strand)
    smallest_strand_size(strand).times.count do |i|
      strand[i] != self[i]
    end
  end

  private

  def smallest_strand_size(strand)
    [strand.length, length].min
  end
end
