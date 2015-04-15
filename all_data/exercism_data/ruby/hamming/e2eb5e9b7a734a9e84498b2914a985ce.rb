class Hamming
  def self.compute original_strand, other_strand
    hamming = new original_strand, other_strand
    hamming.distance
  end

  def initialize original_strand, other_strand
    @original_strand, @other_strand = original_strand, other_strand
  end

  def distance
    if identical_strands?
      0
    else
      counting_the_differences
    end
  end

  private

  def identical_strands?
    @original_strand.eql? @other_strand
  end

  def counting_the_differences
    nuclear_acids.count {|s,t| not s.eql? t }
  end

  def nuclear_acids
    group *bigger_strand_first
  end

  def bigger_strand_first
    @original_strand < @other_strand ? [@original_strand, @other_strand] : [@other_strand, @original_strand]
  end

  def group bigger_strand, lesser_strand
    (bigger_strand.split '').zip lesser_strand.split('')
  end
end
