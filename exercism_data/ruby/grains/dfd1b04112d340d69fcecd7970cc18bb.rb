class Grains
  attr_reader :number_of_squares
  def initialize(side_length=8)
    @number_of_squares = side_length**2
  end

  def square(n)
    2**(n-1)
  end

  def total
    # The *fast* answer is this
    # square(number_of_squares+1)-1
    #
    # But the *logical* way to calculate it is this:
    (1..number_of_squares).map{|n| square(n)}.inject(&:+)
  end
end
