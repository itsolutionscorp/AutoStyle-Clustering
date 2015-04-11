class Hamming
  def self.compute(strand1, strand2)
    @strand1 = strand1
    @strand2 = strand2
    
    mutated_pairs_count
  end


  private

  def self.common_size(strand)
    last_index = [@strand1, @strand2].min.size - 1
    strand[0..last_index]
  end

  def self.common_pairs
    common_size(@strand1).chars.zip(common_size(@strand2).chars)
  end

  def self.mutated_pairs_count
    mutated_pairs = common_pairs.select {|a, b| mutation?(a, b)}
    mutated_pairs.size
  end
  
  def self.mutation?(a, b)
    a != b
  end
end
