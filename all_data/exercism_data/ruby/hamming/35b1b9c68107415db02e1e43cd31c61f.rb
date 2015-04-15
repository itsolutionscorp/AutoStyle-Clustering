class Hamming

  def self.compute(strand1, strand2)                                 # => nil, nil, nil
    combined_strands = together(strand1, strand2)
    count_differences(combined_strands)
  end

  def self.together(strand1, strand2)
    max_index = strand2.length - 1
    strand1.chars[0..max_index].zip(strand2.chars)
  end

  def self.count_differences(combined_strands)
    combined_strands.count{|e1, e2| e1 != e2}
  end
end
