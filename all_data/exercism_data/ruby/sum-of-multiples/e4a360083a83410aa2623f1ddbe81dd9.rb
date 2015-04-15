class Array
  def sum
    inject(:+)
  end
end

class Fixnum
  def multiples_of(number)
    (0..self/number).map {|x| x*number} - [self]
  end
end

class SumOfMultiples
  attr_reader :args

  def initialize(*args)
    @args = args
  end

  def self.to(input)
    new(3,5).to(input)
  end

  def to(num)
    args.inject([]) do |res, ele|
      res << num.multiples_of(ele)
    end.flatten.uniq.sum
  end
end
