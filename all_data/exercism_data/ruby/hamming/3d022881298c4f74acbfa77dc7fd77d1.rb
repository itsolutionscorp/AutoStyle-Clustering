class Hamming

  def self.compute(stranda, strandb)
    count = 0
    self.smallest_strand_size(stranda, strandb).times do |index|
      count += 1 unless strands_match_on_index?(stranda, strandb, index)
    end
    count
  end

  private

  def self.smallest_strand_size(stranda, strandb)
    [stranda, strandb].min.length
  end

  def self.strands_match_on_index?(stranda, strandb, index)
    stranda.chars.to_a[index] == strandb.chars.to_a[index]
  end
end
