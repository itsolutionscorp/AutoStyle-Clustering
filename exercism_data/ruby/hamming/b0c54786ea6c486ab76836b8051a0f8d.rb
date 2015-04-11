class Hamming
  def self.compute(strand1, strand2)
    @strand1 = strand1
    @strand2 = strand2
    
    mutated_pairs_count
  end


  private

  def self.common_sequence(strand)
    last_index = [@strand1, @strand2].min.size - 1
    strand[0..last_index].chars
  end

  def self.common_pairs
    common_sequence(@strand1).zip(common_sequence(@strand2))
  end

  def self.mutated_pairs_count
    mutated_pairs = common_pairs.select {|a, b| mutation?(a, b)}
    mutated_pairs.size
  end
  
  def self.mutation?(a, b)
    a != b
  end
end
