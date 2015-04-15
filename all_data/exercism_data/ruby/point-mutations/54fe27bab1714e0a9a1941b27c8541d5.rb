class DNA
  def initialize strand
    @strand = strand.to_s.upcase
  end
  
  def to_a
    strand.chars
  end
  
  def to_s
    strand
  end
  
  def hamming_distance strand
    Hamming.new(self,DNA.new(strand)).distance
  end
  
  private
  def strand
    @strand
  end
end

class Hamming
  attr_reader :original, :mutation
  def initialize original, mutation
    @original = original
    @mutation = mutation
  end
  
  def distance
    @distance ||= combined_strands.count{|nucleotide_pair| mutated? nucleotide_pair}
  end
  
  private
  
  def combined_strands
    original.to_a.zip(mutation.to_a).take(trim_length)
  end
  
  def trim_length
    [original.to_a.length, mutation.to_a.length].min
  end
  
  def mutated? nucleotide_pair
    nucleotide_pair.first != nucleotide_pair.last
  end
end
