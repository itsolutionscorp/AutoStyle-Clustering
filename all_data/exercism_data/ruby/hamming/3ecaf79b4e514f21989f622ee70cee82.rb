class Hamming
  def self.compute(sequence1, sequence2)
    @counter = 0
    offset(sequence1, sequence2)
  end

  private

  def self.offset(sequence1, sequence2)
    length = matched_length(sequence1, sequence2)
    (0...length).each do |i|
      (@counter += 1) unless sequence1[i].eql?(sequence2[i])
    end
    return @counter
  end

  def self.matched_length(sequence1, sequence2)
    if sequence1.length >= sequence2.length
      sequence2.length
    else
      sequence1.length
    end
  end
end
