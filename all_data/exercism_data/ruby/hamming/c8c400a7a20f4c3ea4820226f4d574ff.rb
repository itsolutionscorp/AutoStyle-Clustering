class Hamming

  def self.compute(strand1, strand2)                                 # => nil, nil, nil
    zipped_strands = together(strand1, strand2)
    count_differences(zipped_strands)
  end

  def self.together(strand1, strand2)
    length = [strand1.length, strand2.length].min
    strand1.chars[0..length-1].zip(strand2.chars[0..length-1])
  end

  def self.count_differences(zipped_strands)
    zipped_strands.count{|e1, e2| e1 != e2}
  end
end
