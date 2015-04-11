class Hamming
  def self.initialize_strands(strand_one, strand_two)
    [strand_one.split(//), strand_two.split(//)]
  end

  def self.ignore_extra_length(strand_one, strand_two)
    strand_one.slice(0,strand_two.size)
  end

  def self.compute(strand_one, strand_two)
    strands = initialize_strands(strand_one, strand_two)

    strand_one = strands[0]
    strand_two = strands[1]
    
    strand_one = ignore_extra_length(strand_one, strand_two)

    # compute hamming distance
    result = strand_one.map.with_index { |letter,idx| letter if letter != strand_two[idx] }
    result.join('').size
  end  
end
