class Point

  def initialize(args)
    @left = args[0]
    @right = args[1]
  end

  def mutation?
    _complete? && !_match?
  end

  private
  attr_reader :left, :right

  def _complete?
    left && right
  end

  def _match?
    left == right
  end
end

class DnaStrand
  def self.parse(strand)
    new(strand.split(''))
  end

  def initialize(bases)
    @bases = bases
  end

  def count_point_mutations_between(other)
    self.calculate_points_with(other).reduce(0) do |result, point|
      result += 1 if point.mutation?
      result
    end
  end

  protected
  attr_reader :bases

  def calculate_points_with(other)
    self.bases.zip(other.bases).map { |pair| Point.new(pair) }
  end

  def length
    bases.length
  end
end

module Hamming
  def self.compute(first, other)
    first_strand = DnaStrand.parse(first)
    other_strand = DnaStrand.parse(other)

    first_strand.count_point_mutations_between(other_strand)
  end
end
