class Squares
  using SequenceMath

  attr_reader :sequence

  def initialize count
    @sequence = (1..count).to_a
  end

  def square_of_sums
    sequence.sum**2
  end

  def sum_of_squares
    sequence.map{|x|x**2}.sum
  end

  def difference
    square_of_sums - sum_of_squares
  end
end

BEGIN {
  module SequenceMath
    refine Array do
      def sum
        reduce(&:+)
      end
    end
  end
}
