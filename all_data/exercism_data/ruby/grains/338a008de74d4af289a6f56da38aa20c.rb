# exercise - ruby/grains
class Grains
  NUM_SQUARES = 64

  def square(n)
    @@squares ||= Array.new(NUM_SQUARES) { |x| 2**x }
    @@squares[n - 1]
  end

  def total
    @@total ||= @@squares.reduce { |a, e| a + e }
    @@total
  end
end
