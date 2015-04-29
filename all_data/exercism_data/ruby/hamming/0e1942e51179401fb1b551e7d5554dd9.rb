class DNA
  def initialize strand 
    self.strand = strand
  end

  def hamming_distance sequence 
    self.comparable_strand = sequence
    return 0 unless strands_to_compare?
    different_pair_total
  end

  private

  def strands_to_compare?
    !strand.empty? && !comparable_strand.empty?
  end

  def different_pair_total
    combined_strands.select{ |a, b| a != b }.size
  end

  def combined_strands
    size = longest_sequence_size
    strand.chars.take(size).zip(comparable_strand.chars.take(size))
  end

  def longest_sequence_size
    if comparable_strand.size < strand.size
     comparable_strand.size
    else
      strand.size
    end
  end

  attr_accessor :comparable_strand, :strand
end
