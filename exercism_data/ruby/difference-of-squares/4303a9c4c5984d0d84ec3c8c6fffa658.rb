class Squares
  using SequenceMath

  def initialize count
    @count = count
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

  private

  def sequence
    (1..@count).to_a
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
