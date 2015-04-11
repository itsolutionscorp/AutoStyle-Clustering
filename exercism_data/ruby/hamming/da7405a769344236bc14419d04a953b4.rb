class DNA
  attr_reader :coding_strand
  
  def initialize(coding_strand)
    @coding_strand = coding_strand
  end

  def hamming_distance(mutated_strand)
    strands = equal_length_strands(coding_strand, mutated_strand)
    
    HammingDistance.compute(*strands)
  end

  private
    def equal_length_strands(original, mutated)
      max_length = [original.length, mutated.length].min

      [original, mutated].map{|s| s[0, max_length]}
    end
end

class HammingDistance
  def self.compute(a, b)
    raise "Strings must be the same length." if a.length != b.length

    a.chars.zip(b.chars).count{|a, b| a != b}
  end
end
