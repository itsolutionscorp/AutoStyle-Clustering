class DNA
  attr_reader :strand
  def initialize strand
    @strand = strand.to_s.upcase
  end
  
  def hamming_distance strand
    Hamming.new(self,DNA.new(strand)).distance
  end
  
  class Hamming
    attr_reader :original, :mutation
    def initialize original, mutation
      @original = original
      @mutation = mutation
    end
    
    def combined_strands
      original.strand.chars.zip(mutation.strand.chars)
    end
    def distance
      combined_strands.count{|points| compare_points points}
    end
    def compare_points points
      points.first != points.last unless points.include? nil
    end
  end
end
