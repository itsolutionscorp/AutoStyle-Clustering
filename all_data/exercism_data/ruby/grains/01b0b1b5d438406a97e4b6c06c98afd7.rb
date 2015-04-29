class Grains
  def initialize
    @@squares ||= Grains.create_squares
  end

  def square(num)
    @@squares[num-1]
  end

  def total
    @@squares.reduce(:+)
  end

  private
    def self.create_squares
      squares = [1]
      63.times { |x| squares.push(squares[x]*2) }
      return squares
    end
end
