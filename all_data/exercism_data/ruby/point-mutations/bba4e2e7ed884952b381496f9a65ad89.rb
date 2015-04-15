class DNA
  def initialize strand
    @strand = strand.to_s.upcase
  end
  
  def to_a
    @strand.chars
  end
  
  def to_s
    @strand
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
      original.to_a.zip(mutation.to_a)
    end
    
    def distance
      combined_strands.count{|points| compare points}
    end
    
    private
    
    def compare points
      points.first != points.last unless points.include? nil
    end
  end
end
