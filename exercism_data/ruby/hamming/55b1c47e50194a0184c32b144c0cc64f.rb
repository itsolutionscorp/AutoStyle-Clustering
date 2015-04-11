module Hamming
  def self.compute(strand1, strand2)
    @@strand1 = strand1
    @@strand2 = strand2
    
    mutated_pairs_count
  end


  private

  def self.mutated_pairs_count
    common_pairs.count {|a, b| mutation?(a, b)}
  end
  
  def self.common_pairs
    common_sequence(@@strand1).zip(common_sequence(@@strand2))
  end

  def self.mutation?(a, b)
    a != b
  end

  def self.common_length
    [@@strand1.length, @@strand2.length].min
  end

  def self.common_sequence(strand)
    strand.chars.take common_length
  end
end
