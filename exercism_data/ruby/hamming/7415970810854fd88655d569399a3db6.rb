class Hamming
  def self.compute(sequence1, sequence2)
    @counter = 0
    offset(sequence1, sequence2)
  end

  private

  def self.offset(sequence1, sequence2)
    length = [sequence1.length, sequence2.length].min
    length.times.count { |i| !sequence1[i].eql? sequence2[i] }
  end
end
