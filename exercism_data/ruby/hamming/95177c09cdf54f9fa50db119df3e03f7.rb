class Hamming
  def self.compute(strand0, strand1)
    strands = [strand0, strand1].sort_by {|x| x.length}
    count_diffs strands[0].chars.zip(strands[1].chars)
  end

  def self.count_diffs(array)
    array.count { |elem| elem[0] != elem[1] }
  end
end
