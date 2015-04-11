class Grains

  def initialize
    @squares = 64
  end

  def square(num)
    2**(num-1)
  end

  def total
    (1..@squares).inject(0) { |result, element| result += square(element) }
  end
end
