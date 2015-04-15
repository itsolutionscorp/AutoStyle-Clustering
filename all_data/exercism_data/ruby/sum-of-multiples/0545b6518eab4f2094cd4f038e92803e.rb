class SumOfMultiples
  def initialize (*xs)
    @xs = xs
  end
  def SumOfMultiples.to (n)
    SumOfMultiples.new(3, 5).to(n)
  end
  def to (n)
    (1...n).select{|k|
      @xs.map{|i| (k % i).zero?}.any?
    }.reduce(0, :+)
  end
end
