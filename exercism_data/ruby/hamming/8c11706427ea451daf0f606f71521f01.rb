class Hamming
  include Enumerable

  attr_reader :strand

  def self.compute(strand, other_strand)
    new(strand).distance_from(new(other_strand))
  end

  def initialize(strand)
    @strand = strand
  end

  def distance_from(other_strand)
    zip(other_strand).select { |p1, p2| p2 && (p1 != p2) }.count
  end

  def each(&block)
    points.each(&block)
  end

  private

  def points
    strand.chars
  end
end
