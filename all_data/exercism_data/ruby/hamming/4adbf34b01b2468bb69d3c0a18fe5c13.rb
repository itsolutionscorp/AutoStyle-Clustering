class Hamming

  def self.compute(strand1, strand2)                                 # => nil, nil, nil
    zipped_strands = together(strand1, strand2)
    count_differences(zipped_strands)
  end

  def self.together(strand1, strand2)
    if strand1.length < strand2.length
      tog = strand1.split('').zip(strand2.split(''))
    else
      tog = strand2.split('').zip(strand1.split(''))
    end
    return(tog)
  end

  def self.count_differences(zipped_strands)
    diff = 0
    zipped_strands.each do |x|
      unless x[0] == x[1]
        diff += 1
      end
    end
    return diff
  end
end
