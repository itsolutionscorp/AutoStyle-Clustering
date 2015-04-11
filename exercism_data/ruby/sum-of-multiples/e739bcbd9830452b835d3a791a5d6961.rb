class SumOfMultiples

  def initialize(*multiples)
    @multiples = multiples
  end

  def self.to(max)
    sum = (1...max).select {|num| num % 3 == 0 || num % 5 == 0}.reduce(:+)
    sum ? sum : 0
  end

  def to(max)
    @multiples.map {|multiple| (1...max).select {|num| num % multiple == 0} }.flatten.uniq.reduce(:+)
  end
end
