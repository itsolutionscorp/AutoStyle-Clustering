class EnumerableComparer
  def initialize list1, list2
    @list1 = list1
    @list2 = list2
  end

  def hamming_distance
    side_by_side.count {|a,b| a != b }
  end

  def side_by_side
    list1_truncated.zip(list2_truncated)
  end

  def list1_truncated
    @list1.take(minimum_length)
  end

  def list2_truncated
    @list2.take(minimum_length)
  end

  def minimum_length
    [@list1.length, @list2.length].min
  end
end


class DNA
  def initialize strand
    @strand = strand
  end

  def hamming_distance other_strand
    EnumerableComparer.new(@strand.chars, other_strand.chars).hamming_distance
  end
end
