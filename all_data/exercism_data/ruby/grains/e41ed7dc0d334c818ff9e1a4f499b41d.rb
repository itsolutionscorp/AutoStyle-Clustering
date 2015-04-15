class Grains
  def initialize
    @squares = Hash.new do |hash, key|
      hash[key] = 2**(key-1)
    end
  end

  def square n
    @squares[n]
  end

  def total
    (1..64).map {|i| square i}.reduce :+
  end
end
