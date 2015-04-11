class Hamming
  def self.compute original_strand, other_strand
    hamming = new original_strand, other_strand
    hamming.distance
  end

  def initialize original_strand, other_strand
    @original_strand, @other_strand = original_strand, other_strand
  end

  def distance
    nuclear_acids.count {|s,t| not s.eql? t }
  end

  private

  def nuclear_acids
    group *put_bigger_first
  end

  def put_bigger_first
    @original_strand< @other_strand ? [@original_strand, @other_strand] : [@other_strand, @original_strand]
  end

  def group bigger, lesser
    split(bigger).zip split(lesser)
  end

  def split strand
    strand.split ''
  end
end
