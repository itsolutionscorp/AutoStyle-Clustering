# exercise - ruby/grains
class Grains
  NUM_SQUARES = 64

  def initialize
    @@squares ||= Array.new(NUM_SQUARES) { |x| 2**x }
    @@total ||= @@squares.reduce { |a, e| a + e }
  end

  def square(n)
    @@squares[n - 1]
  end

  def total
    @@total
  end
end
