class Squares

  attr_reader :collection

  def initialize limit
    @collection = ( 1..limit ).to_a
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def square_of_sums
    collection.reduce( &:+ ) ** 2
  end

  def sum_of_squares
    collection_of_squares.reduce( &:+ )
  end

private

  def collection_of_squares
    collection.map { |element| element ** 2 }
  end

end
