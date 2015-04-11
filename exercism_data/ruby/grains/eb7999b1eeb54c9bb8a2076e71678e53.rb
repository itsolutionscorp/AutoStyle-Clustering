class Grains
  N = 64
  @@squares = [0, *0...N].map { |i| 2**i }
  @@total = @@squares.inject(0) { |sum,i| sum+i} - 1 # ignore @@squares[0]

  def square n
    @@squares[n]
  end

  def total
    @@total
  end
end
